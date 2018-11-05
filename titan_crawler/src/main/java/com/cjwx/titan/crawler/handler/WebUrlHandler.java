package com.cjwx.titan.crawler.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.crawler.bean.ClrPageBean;
import com.cjwx.titan.crawler.bean.ClrWebUrlBean;
import com.cjwx.titan.crawler.service.WebUrlService;
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
@RestHandler("网页爬虫-页面管理")
@RequestMapping(value = "/webUrl/", method = RequestMethod.POST)
public class WebUrlHandler {

    @Resource
    private WebUrlService webUrlService;

    @RequestMapping("list")
    public PageList<ClrWebUrlBean> list(@RequestBody Model model) {
        return webUrlService.getWebUrlList(model.getStart(), model.getSize(), model.getParams(ClrWebUrlBean.class));
    }

    @RequestMapping("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return webUrlService.deleteWebUrl(ids);
    }

    public void create(ClrPageBean page) {
        webUrlService.createPage(page);
    }

}
