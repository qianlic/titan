package com.cjwx.titan.crawler.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.crawler.service.CrawlerService;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@RestHandler("网页爬虫-爬虫管理")
@RequestMapping(value = "/crawler/", method = RequestMethod.POST)
public class CrawlerHandler {

    @Resource
    private CrawlerService crawlerService;

    @RequestMapping("list")
    public PageList<ClrCrawlerBean> list(@RequestBody Model model) {
        return crawlerService.getCrawlerList(model.getStart(), model.getSize(), model.getParams(ClrCrawlerBean.class));
    }

    @RequestMapping("create")
    public void create(@RequestBody ClrCrawlerBean crawler) {
        crawlerService.createCrawler(crawler);
    }

    @RequestMapping("edit")
    public int edit(@RequestBody Model model) {
        return crawlerService.updateCrawler(model.getId(), model.getParams(ClrCrawlerBean.class));
    }

    @RequestMapping("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return crawlerService.deleteCrawler(ids);
    }

    @RequestMapping("status")
    public int status(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        boolean status = model.getBoolean("status");
        return crawlerService.updateStatus(ids, status);
    }

    @RequestMapping("run")
    public void run(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        crawlerService.execute(ids);
    }

}
