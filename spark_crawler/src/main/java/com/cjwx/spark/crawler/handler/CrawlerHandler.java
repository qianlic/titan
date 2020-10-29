package com.cjwx.spark.crawler.handler;

import com.cjwx.spark.crawler.entity.ClrCrawler;
import com.cjwx.spark.crawler.service.CrawlerService;
import com.cjwx.spark.engine.util.Model;
import com.cjwx.spark.engine.util.PageList;
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
    public PageList<ClrCrawler> list(@RequestBody Model<ClrCrawler> model) {
        return crawlerService.getCrawlerList(model.getStart(), model.getSize(), model.getParams());
    }

    @RestMethod("run")
    public void run(@RequestBody List<Long> ids) {
        crawlerService.execute(ids);
    }

}
