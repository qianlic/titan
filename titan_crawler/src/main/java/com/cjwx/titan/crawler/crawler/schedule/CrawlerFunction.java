package com.cjwx.titan.crawler.crawler.schedule;

import org.jsoup.nodes.Document;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月15日 17:13
 */
@FunctionalInterface
public interface CrawlerFunction {

    void process(UrlSeed seed, Document document);

}
