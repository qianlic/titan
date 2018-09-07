package com.cjwx.titan.engine.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 请求操作
 * @Author: qian li
 * @Date: 2018年08月30日 15:49
 */
@Slf4j
public class HttpClientUtils {

    public static final String CHARSET_CODE = "UTF-8";
    public static final String APPLICATION_JOSN = "application/json";

    /**
     * 执行表单提交
     *
     * @param url   请求连接
     * @param param 提交参数
     */
    public static String doFromRequest(String url, Map<String, Object> param) {
        String result = StringUtils.NULL_STRING;
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        FilePart fp;
        try {
            List<Part> parts = new ArrayList<>();
            for (String k : param.keySet()) {
                Object v = param.get(k);
                if (v instanceof File) {
                    FilePart f = new FilePart(k, (File) v);
                    parts.add(f);
                } else {
                    StringPart nv = new StringPart(k, v.toString());
                    parts.add(nv);
                }
            }
            MultipartRequestEntity entity = new MultipartRequestEntity(parts.toArray(new Part[parts.size()]), new HttpMethodParams());
            post.setRequestEntity(entity);
            client.executeMethod(post);
            InputStream inputStream = post.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            result = stringBuffer.toString();
        } catch (Exception e) {
            log.debug("请求异常：", e);
        }
        return result;
    }

    /**
     * 执行http请求
     *
     * @param url    请求连接
     * @param method 请求方法
     * @param param  提交参数
     */
    private static String dohttpRequest(String url, String method, Map<String, Object> param) {
        String result = StringUtils.NULL_STRING;
        HttpURLConnection conn = null;
        BufferedReader br = null;
        OutputStream os = null;
        try {
            if ("GET".equalsIgnoreCase(method) && ObjectUtils.isNotEmpty(param)) {
                url = url + "?" + param.entrySet().stream()
                        .map(e -> e.getKey() + "=" + e.getValue())
                        .collect(Collectors.joining("&"));
            }
            URL uri = new URL(url);
            conn = enableHttps(uri);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", APPLICATION_JOSN + "; charset=" + CHARSET_CODE);
            if ("POST".equalsIgnoreCase(method)) {
                conn.setRequestMethod("POST");
                conn.setReadTimeout(30 * 1000); // 缓存的最长时间
                conn.setDoInput(true);// 允许输入
                conn.setDoOutput(true);// 允许输出
                conn.setUseCaches(false); // 不允许使用缓存
                if (ObjectUtils.isNotEmpty(param)) {
                    byte[] writebytes = JSON.toJSONString(param).getBytes();
                    conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                    os = conn.getOutputStream();
                    os.write(writebytes);
                    os.flush();
                    os.close();
                }
            }
            if (conn.getResponseCode() == HttpStatus.SC_OK) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET_CODE));
                StringBuffer stringBuffer = new StringBuffer();
                String str;
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                result = stringBuffer.toString();
            }
        } catch (Exception e) {
            log.debug("请求异常：", e);
        } finally {
            try {
                if (br != null) br.close();
                if (os != null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (conn != null) conn.disconnect();
        }
        return result;
    }

    /**
     * 获取http连接
     */
    private static HttpURLConnection enableHttps(URL url) throws Exception {
        if (!"https".equalsIgnoreCase(url.getProtocol())) {
            return (HttpURLConnection) url.openConnection();
        }
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        // Prepare SSL Context
        TrustManager[] tm = {ignoreCertificationTrustManger};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        connection.setSSLSocketFactory(ssf);
        return connection;
    }

    /**
     * 忽视HostName
     */
    private static HostnameVerifier ignoreHostnameVerifier = (s, sslsession) -> {
        System.out.println("WARNING: Hostname is not matched for cert.");
        return true;
    };

    /**
     * 忽视证书
     */
    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate certificates[], String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] ax509certificate, String s) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

}
