package com.cjwx.titan.crawler.robotstxt;

import com.cjwx.titan.crawler.crawler.exceptions.PageBiggerThanMaxSizeException;
import com.cjwx.titan.crawler.fetcher.Page;
import com.cjwx.titan.crawler.fetcher.PageFetchResult;
import com.cjwx.titan.crawler.fetcher.PageFetcher;
import com.cjwx.titan.crawler.url.WebURL;
import com.cjwx.titan.crawler.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;

import java.net.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RobotstxtServer {

    protected RobotstxtConfig config;

    protected final Map<String, HostDirectives> host2directivesCache = new HashMap<>();

    protected PageFetcher pageFetcher;

    public RobotstxtServer(RobotstxtConfig config, PageFetcher pageFetcher) {
        this.config = config;
        this.pageFetcher = pageFetcher;
    }

    private static String getHost(URL url) {
        return url.getHost().toLowerCase();
    }

    public boolean allows(WebURL webURL) {
        if (config.isEnabled()) {
            try {
                URL url = new URL(webURL.getUrl());
                String host = getHost(url);
                String path = url.getPath();
                HostDirectives directives = host2directivesCache.get(host);
                //判断指令过期刷新
                if ((directives != null) && directives.needsRefetch()) {
                    synchronized (host2directivesCache) {
                        host2directivesCache.remove(host);
                        directives = null;
                    }
                }
                if (directives == null) {
                    directives = fetchDirectives(url);
                }
                return directives.allows(path);
            } catch (MalformedURLException e) {
                log.error("Bad URL in Robots.txt: " + webURL.getUrl(), e);
            }
        }
        return true;
    }

    //获取主机robots文件配置的目录
    private HostDirectives fetchDirectives(URL url) {
        WebURL robotsTxtUrl = new WebURL();
        String host = getHost(url);
        String port = ((url.getPort() == url.getDefaultPort()) || (url.getPort() == -1)) ? "" : (":" + url.getPort());
        robotsTxtUrl.setURL("http://" + host + port + "/robots.txt");
        HostDirectives directives = null;
        PageFetchResult fetchResult = null;
        try {
            fetchResult = pageFetcher.fetchPage(robotsTxtUrl);
            if (fetchResult.getStatusCode() == HttpStatus.SC_OK) {
                Page page = new Page(robotsTxtUrl);
                fetchResult.fetchContent(page);
                if (ConvertUtil.hasPlainTextContent(page.getContentType())) {
                    directives = RobotstxtParser.parse(page.getContent(), config.getUserAgentName());
                } else if (ConvertUtil.hasPlainHtmlContent(page.getContentType())) {
                    String content = new String(page.getContentData());
                    directives = RobotstxtParser.parse(content, config.getUserAgentName());
                } else {
                    log.warn("Can't read this robots.txt: {}  as it is not written in plain text, contentType: {}", robotsTxtUrl.getUrl(), page.getContentType());
                }
            } else {
                log.debug("Can't read this robots.txt: {}  as it's state code is {}", robotsTxtUrl.getUrl(), fetchResult.getStatusCode());
            }
        } catch (SocketException | UnknownHostException | SocketTimeoutException | NoHttpResponseException se) {
        } catch (PageBiggerThanMaxSizeException pbtms) {
            log.error("Error occurred while fetching (robots) url: {}, {}", robotsTxtUrl.getUrl(), pbtms.getMessage());
        } catch (Exception e) {
            log.error("Error occurred while fetching (robots) url: " + robotsTxtUrl.getUrl(), e);
        } finally {
            if (fetchResult != null) {
                fetchResult.discardContentIfNotConsumed();
            }
        }
        if (directives == null) {
            directives = new HostDirectives();
        }
        //存放robots内容
        synchronized (host2directivesCache) {
            if (host2directivesCache.size() == config.getCacheSize()) {
                String minHost = null;
                long minAccessTime = Long.MAX_VALUE;
                for (Map.Entry<String, HostDirectives> entry : host2directivesCache.entrySet()) {
                    if (entry.getValue().getLastAccessTime() < minAccessTime) {
                        minAccessTime = entry.getValue().getLastAccessTime();
                        minHost = entry.getKey();
                    }
                }
                host2directivesCache.remove(minHost);
            }
            host2directivesCache.put(host, directives);
        }
        return directives;
    }

}
