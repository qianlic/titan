package com.cjwx.titan.crawler.dao;

import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.engine.core.model.PageList;

import java.util.List;
import java.util.Map;

public interface CrawlerDao {

    void batchInsert(List bean);

    int delete(List ids);

    int update(int id, Map<String, Object> set);

    int update(List ids, boolean status);

    List<ClrCrawlerBean> select();

    List<ClrCrawlerBean> select(List<String> ids);

    PageList<ClrCrawlerBean> select(int start, int size, Map<String, Object> where);

}
