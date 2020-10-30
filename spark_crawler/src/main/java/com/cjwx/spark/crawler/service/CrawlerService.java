package com.cjwx.spark.crawler.service;

import com.cjwx.spark.crawler.dto.ClrCrawlerDTO;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;

import java.util.List;

public interface CrawlerService {

    ResultDTO<List<ClrCrawlerDTO>> getCrawlerList() throws Exception;

    ResultDTO<PageDTO<ClrCrawlerDTO>> getCrawlerList(ClrCrawlerDTO crawler, int start, int size) throws Exception;

    void execute(List<Long> ids);

    void batchInsert(List<ClrCrawlerDTO> crawlers);

}
