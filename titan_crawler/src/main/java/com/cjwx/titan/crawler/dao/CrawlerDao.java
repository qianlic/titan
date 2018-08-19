package com.cjwx.titan.crawler.dao;

import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.engine.core.model.PageList;

import java.util.List;
import java.util.Map;

public interface CrawlerDao {

    void createCrawler(ClrCrawlerBean crawler);

    int deleteCrawler(List ids);

    int updateCrawler(int id, Map<String, Object> set);

    int updateStatus(List ids, boolean status);

    List<ClrCrawlerBean> findCrawlerByIds(List<String> ids);

    PageList<ClrCrawlerBean> findCrawlerList(int start, int size, Map<String, Object> whereCondition);

}
