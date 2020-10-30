package com.cjwx.spark.crawler.handler;

import com.cjwx.spark.crawler.dto.ClrCrawlerDTO;
import com.cjwx.spark.crawler.service.CrawlerService;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@Api(tags = "网页爬虫-爬虫管理")
@RestHandler("/crawler/")
public class CrawlerHandler {

    @Resource
    private CrawlerService crawlerService;

    @RestMethod("list")
    public ResultDTO<PageDTO<ClrCrawlerDTO>> list(@RequestBody ClrCrawlerDTO crawler) throws Exception {
        return crawlerService.getCrawlerList(crawler, crawler.getStart(), crawler.getSize());
    }

    @RestMethod("run")
    public void run(@RequestBody List<Long> ids) {
        crawlerService.execute(ids);
    }

}
