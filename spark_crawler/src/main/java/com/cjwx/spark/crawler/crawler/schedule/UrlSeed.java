package com.cjwx.spark.crawler.crawler.schedule;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月16日 16:12
 */
@Data
public class UrlSeed implements Serializable {

    private static final long serialVersionUID = -6833954655709730453L;

    private boolean history = false;
    private Integer type = 0;
    private String crawler;
    private String url;

    public UrlSeed(String crawler) {
        this.crawler = crawler;
    }

    public UrlSeed(String crawler, String url) {
        this(crawler);
        this.url = url;
    }

    public UrlSeed(String crawler, Integer type, String url) {
        this(crawler, url);
        this.type = type;
    }

    public UrlSeed(String crawler, String url, boolean history) {
        this(crawler, url);
        this.history = history;
    }

}
