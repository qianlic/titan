package com.cjwx.titan.crawler.service;

import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.engine.core.model.PageList;

import java.util.List;
import java.util.Map;

public interface CrawlerService {

    void execute(List<String> ids);

    void batchInsert(List<ClrCrawlerBean> crawlers);

    List<ClrCrawlerBean> getCrawlerList();

    PageList<ClrCrawlerBean> getCrawlerList(int start, int size, Map<String, Object> whereCondition);

}
