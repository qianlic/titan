package com.cjwx.titan.crawler.service;

import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.engine.core.model.PageList;

import java.util.List;
import java.util.Map;

public interface CrawlerService {

    void execute(List ids);

    void createCrawler(ClrCrawlerBean crawler);

    int deleteCrawler(List ids);

    int updateCrawler(int id, Map<String, Object> set);

    int updateStatus(List ids, boolean status);

    PageList<ClrCrawlerBean> getCrawlerList(int start, int size, Map<String, Object> whereCondition);

    List<ClrCrawlerBean> findCrawlerByIds(String ids);

}
