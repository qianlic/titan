package com.cjwx.titan.crawler.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.crawler.service.CrawlerService;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

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
    public PageList<ClrCrawlerBean> list(@RequestBody Model model) {
        return crawlerService.getCrawlerList(model.getStart(), model.getSize(), model.getParams(ClrCrawlerBean.class));
    }

    @RestMethod("run")
    public void run(@RequestBody Model model) {
        crawlerService.execute(model.getJSONArray("ids").toJavaList(String.class));
    }

}
