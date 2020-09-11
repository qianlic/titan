package com.cjwx.spark.crawler.service;

import com.cjwx.spark.crawler.entity.ClrCrawlerEntity;
import com.cjwx.spark.engine.core.model.PageList;

import java.util.List;

public interface CrawlerService {

    void execute(List<Long> ids);

    void batchInsert(List<ClrCrawlerEntity> crawlers);

    List<ClrCrawlerEntity> getCrawlerList();

    PageList<ClrCrawlerEntity> getCrawlerList(int start, int size, ClrCrawlerEntity crawler);

}
