package com.cjwx.titan.crawler.util;

import com.cjwx.titan.crawler.crawler.schedule.CrawlerFunction;
import com.cjwx.titan.crawler.crawler.schedule.UrlSeed;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月14日 14:50
 */
@Slf4j
public class JsoupUtils {

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";

    public static void doGet(CrawlerFunction function, UrlSeed url) {
        if (function != null) {
            function.process(url, doGet(url.getUrl()));
        }
    }

    public static Document doGet(String url) {
        try {
            return connect(url).get();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return null;
    }

    public static Connection connect(String url) {
        return Jsoup.connect(url).userAgent(USER_AGENT);
    }

}
