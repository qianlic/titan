package com.cjwx.titan.crawler.fetcher;

import com.cjwx.titan.crawler.crawler.Configurable;
import com.cjwx.titan.crawler.crawler.CrawlConfig;
import com.cjwx.titan.crawler.crawler.authentication.AuthInfo;
import com.cjwx.titan.crawler.crawler.authentication.impl.BasicAuthInfo;
import com.cjwx.titan.crawler.crawler.authentication.impl.FormAuthInfo;
import com.cjwx.titan.crawler.crawler.authentication.impl.NtAuthInfo;
import com.cjwx.titan.crawler.crawler.exceptions.PageBiggerThanMaxSizeException;
import com.cjwx.titan.crawler.url.URLCanonicalizer;
import com.cjwx.titan.crawler.url.WebURL;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//页面抓取器
@Slf4j
public class PageFetcher extends Configurable {

    protected PoolingHttpClientConnectionManager connectionManager;
    protected CloseableHttpClient httpClient;
    protected final Object mutex = new Object();
    protected long lastFetchTime = 0;
    protected IdleConnectionMonitorThread connectionMonitorThread;

    public PageFetcher(CrawlConfig config) {
        super(config);
        //socket连接工厂
        RegistryBuilder<ConnectionSocketFactory> connRegistryBuilder = RegistryBuilder.create();
        connRegistryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE);
        //处理https协议
        if (config.isIncludeHttpsPages()) {
            try {
                //指定信任密钥存储对象 信任所有
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (chain, authType) -> true).build();
                //构造SSL连接套接字工厂
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                //将自定义SSL连接工厂注册到https协议
                connRegistryBuilder.register("https", sslsf);
            } catch (Exception e) {
                log.debug("Exception thrown while trying to register https", e);
            }
        }
        Registry<ConnectionSocketFactory> connRegistry = connRegistryBuilder.build();
        //设置连接管理器
        connectionManager = new PoolingHttpClientConnectionManager(connRegistry);
        connectionManager.setMaxTotal(config.getMaxTotalConnections());
        connectionManager.setDefaultMaxPerRoute(config.getMaxConnectionsPerHost());
        //配置网络环境
        RequestConfig requestConfig = RequestConfig.custom()
                .setExpectContinueEnabled(false)//关闭Expect:100-Continue握手
                .setCookieSpec(CookieSpecs.DEFAULT)//设置CookieSpec规范
                .setRedirectsEnabled(false)//关闭跳转
                .setSocketTimeout(config.getSocketTimeout())//设置Socket连接超时时间
                .setConnectTimeout(config.getConnectionTimeout()).build();//设置连接超时时间
        //构建客户端
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.setDefaultRequestConfig(requestConfig);
        clientBuilder.setConnectionManager(connectionManager);
        clientBuilder.setUserAgent(config.getUserAgentString());
        clientBuilder.setDefaultHeaders(config.getDefaultHeaders());
        //设置代理
        if (config.getProxyHost() != null) {
            HttpHost proxy = new HttpHost(config.getProxyHost(), config.getProxyPort());
            clientBuilder.setProxy(proxy);
            if (config.getProxyUsername() != null) {
                //创建用户凭证
                BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                AuthScope authScope = new AuthScope(proxy);
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(config.getProxyUsername(), config.getProxyPassword());
                credentialsProvider.setCredentials(authScope, credentials);
                clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
            log.debug("Working through Proxy: {}", proxy.getHostName());
        }
        httpClient = clientBuilder.build();
        //登录认证
        if ((config.getAuthInfos() != null) && !config.getAuthInfos().isEmpty()) {
            doAuthetication(config.getAuthInfos());
        }
        connectionMonitorThread = new IdleConnectionMonitorThread(connectionManager);
        connectionMonitorThread.start();
    }

    private void doAuthetication(List<AuthInfo> authInfos) {
        authInfos.stream().forEach(authInfo -> {
            if (authInfo.getAuthenticationType() == AuthInfo.AuthenticationType.BASIC_AUTHENTICATION) {
                doBasicLogin((BasicAuthInfo) authInfo);
            } else if (authInfo.getAuthenticationType() == AuthInfo.AuthenticationType.NT_AUTHENTICATION) {
                doNtLogin((NtAuthInfo) authInfo);
            } else {
                doFormLogin((FormAuthInfo) authInfo);
            }
        });
    }

    private void doBasicLogin(BasicAuthInfo authInfo) {
        log.info("BASIC authentication for: " + authInfo.getLoginTarget());
        HttpHost targetHost = new HttpHost(authInfo.getHost(), authInfo.getPort(), authInfo.getProtocol());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();

        AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
        UsernamePasswordCredentials basicCredentials = new UsernamePasswordCredentials(authInfo.getUsername(), authInfo.getPassword());
        credsProvider.setCredentials(authScope, basicCredentials);

        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
    }

    private void doNtLogin(NtAuthInfo authInfo) {
        log.info("NT authentication for: " + authInfo.getLoginTarget());
        HttpHost targetHost = new HttpHost(authInfo.getHost(), authInfo.getPort(), authInfo.getProtocol());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        try {
            AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
            NTCredentials ntCredentials = new NTCredentials(authInfo.getUsername(), authInfo.getPassword(), InetAddress.getLocalHost().getHostName(), authInfo.getDomain());
            credsProvider.setCredentials(authScope, ntCredentials);
        } catch (UnknownHostException e) {
            log.error("Error creating NT credentials", e);
        }
        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
    }

    private void doFormLogin(FormAuthInfo authInfo) {
        log.info("FORM authentication for: " + authInfo.getLoginTarget());
        String fullUri = authInfo.getProtocol() + "://" + authInfo.getHost() + ":" + authInfo.getPort() + authInfo.getLoginTarget();
        HttpPost httpPost = new HttpPost(fullUri);
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair(authInfo.getUsernameFormStr(), authInfo.getUsername()));
        formParams.add(new BasicNameValuePair(authInfo.getPasswordFormStr(), authInfo.getPassword()));

        if (!"".equals(authInfo.getCaptcha()) && !"".equals(authInfo.getCaptchaFormStr())) {
            formParams.add(new BasicNameValuePair(authInfo.getCaptchaFormStr(), authInfo.getCaptcha()));
        }

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(entity);
            httpClient.execute(httpPost);
            log.debug("Successfully Logged in with user: " + authInfo.getUsername() + " to: " + authInfo.getHost());
        } catch (UnsupportedEncodingException e) {
            log.error("Encountered a non supported encoding while trying to login to: " + authInfo.getHost(), e);
        } catch (ClientProtocolException e) {
            log.error("While trying to login to: " + authInfo.getHost() + " - Client protocol not supported", e);
        } catch (IOException e) {
            log.error("While trying to login to: " + authInfo.getHost() + " - Error making request", e);
        }
    }

    //获取URL返回
    public PageFetchResult fetchPage(WebURL webUrl) throws InterruptedException, IOException, PageBiggerThanMaxSizeException {
        PageFetchResult fetchResult = new PageFetchResult();
        String toFetchURL = webUrl.getUrl();
        HttpUriRequest request = null;
        try {
            request = newHttpUriRequest(toFetchURL);
            //判断fetch url请求间隔
            synchronized (mutex) {
                long interval = (new Date()).getTime() - lastFetchTime;
                if (interval < config.getPolitenessDelay()) {
                    Thread.sleep(config.getPolitenessDelay() - interval);
                }
                lastFetchTime = (new Date()).getTime();
            }

            CloseableHttpResponse response = httpClient.execute(request);
            fetchResult.setEntity(response.getEntity());
            fetchResult.setResponseHeaders(response.getAllHeaders());
            fetchResult.setFetchedUrl(toFetchURL);

            int statusCode = response.getStatusLine().getStatusCode();
            //判断页面是否跳转
            if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
                    || statusCode == HttpStatus.SC_MOVED_TEMPORARILY
                    || statusCode == HttpStatus.SC_MULTIPLE_CHOICES
                    || statusCode == HttpStatus.SC_SEE_OTHER
                    || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT
                    || statusCode == 308) {
                //获取跳转后地址
                Header header = response.getFirstHeader("Location");
                if (header != null) {
                    String movedToUrl = URLCanonicalizer.getCanonicalURL(header.getValue(), toFetchURL);
                    fetchResult.setMovedToUrl(movedToUrl);
                }
            } else if (statusCode >= 200 && statusCode <= 299) {
                if (fetchResult.getEntity() != null) {
                    long size = fetchResult.getEntity().getContentLength();
                    if (size == -1) {
                        Header length = response.getLastHeader("Content-Length");
                        if (length != null) {
                            size = Integer.parseInt(length.getValue());
                        }
                    }
                    if (size > config.getMaxDownloadSize()) {
                        response.close();
                        throw new PageBiggerThanMaxSizeException(size);
                    }
                }
            }
            fetchResult.setStatusCode(statusCode);
            return fetchResult;
        } finally {
            if ((fetchResult.getEntity() == null) && (request != null)) {
                request.abort();
            }
        }
    }

    public synchronized void shutDown() {
        if (connectionMonitorThread != null) {
            connectionManager.shutdown();
            connectionMonitorThread.shutdown();
        }
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    protected HttpUriRequest newHttpUriRequest(String url) {
        String cookie = "pgv_pvi=5885268886; " +
                "CNZZDATA334992=cnzz_eid%3D2063542172-1459679366-http%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1459761000; " +
                "pgv_info=ssi=s1829916320; " +
                "2nSC_2132_sid=IO72mZ; " +
                "2nSC_2132_lastvisit=1459755350; " +
                "2nSC_2132_lastact=1459762415%09home.php%09spacecp; " +
                "2nSC_2132_sendmail=1; " +
                "2nSC_2132_delay=1459762393; " +
                "2nSC_2132_pwdlevel=3; " +
                "2nSC_2132_ulastactivity=fca2fL%2FQ1lDAFJy73noUXx6Lftp0%2BK0QVbsaDM5sV43wbmJ34aBJ; " +
                "2nSC_2132_lastcheckfeed=579478%7C1459762414; " +
                "2nSC_2132_checkfollow=1; " +
                "8fl9_auth=8408IV%2BcM4Rd%2B0Iw92Q4QwgtaOiBoJG%2BciDvfVj9SzeK%2F8mJ; " +
                "2nSC_2132_connect_is_bind=0; " +
                "2nSC_2132_nofavfid=1; " +
                "2nSC_2132_onlineusernum=19369; " +
                "2nSC_2132_checkpm=1";
        BasicHeader bh = new BasicHeader("Cookie", cookie);
        HttpGet get = new HttpGet(url);
        get.addHeader(bh);
        return get;
    }

}
