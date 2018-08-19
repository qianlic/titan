package com.cjwx.titan.crawler.crawler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月15日 9:35
 */
public class CrawlConstant {

    private static Map<String, CrawlController> crawlerContainerMap = new HashMap<>();

    public static boolean contains(String name) {
        return crawlerContainerMap.containsKey(name);
    }

    public static CrawlController get(String name) {
        return crawlerContainerMap.get(name);
    }

    public static void put(String name, CrawlController crawler) {
        crawlerContainerMap.put(name, crawler);
    }

}
