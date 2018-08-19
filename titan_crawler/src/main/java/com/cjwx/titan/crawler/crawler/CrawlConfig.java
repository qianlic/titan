package com.cjwx.titan.crawler.crawler;

import com.cjwx.titan.crawler.crawler.authentication.AuthInfo;
import lombok.Data;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CrawlConfig {

    //可恢复的
    private boolean resumableCrawling = false;
    //队列空自动关闭
    private boolean shutdownOnEmptyQueue = true;

    private int maxDepthOfCrawling = -1;

    //二进制内容，如图片
    private boolean includeBinaryContentInCrawling = false;
    private boolean processBinaryContentInCrawling = false;
    //运行跳转
    private boolean followRedirects = true;
    private int maxOutgoingLinksToFollow = 5000;

    //用户代理：客户端浏览器的类型版本
    private String userAgentString = "Mozilla/5.0 (Macintosh;intel Mac OS X 10_7_0) AppleWebKit/535.11(KHTML,like Gecko) Chrome/17.0.963.56 Safari/535.11";
    //自定义header
    private Collection<BasicHeader> defaultHeaders = new HashSet<>();
    //代理配置
    private String proxyHost = null;
    private int proxyPort = 80;
    private String proxyUsername = null;
    private String proxyPassword = null;
    //认证
    private List<AuthInfo> authInfos;
    //包含https页面
    private boolean includeHttpsPages = true;
    //连接超时时间
    private int socketTimeout = 20000;
    private int connectionTimeout = 30000;
    //最大连接数
    private int maxTotalConnections = 100;
    //每个路由基础连接数
    private int maxConnectionsPerHost = 100;
    //PageFetch间隔时间
    private int politenessDelay = 200;
    private int maxDownloadSize = 1048576;

    public Collection<BasicHeader> getDefaultHeaders() {
        return new HashSet<>(defaultHeaders);
    }

    public void setDefaultHeaders(Collection<? extends Header> defaultHeaders) {
        this.defaultHeaders = defaultHeaders.stream()
                .map(header -> new BasicHeader(header.getName(), header.getValue()))
                .collect(Collectors.toList());
    }

    public void addAuthInfo(AuthInfo authInfo) {
        if (this.authInfos == null) {
            this.authInfos = new ArrayList<>();
        }
        this.authInfos.add(authInfo);
    }

}
