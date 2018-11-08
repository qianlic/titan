package com.cjwx.titan.crawler.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.crawler.bean.ClrPageBean;
import com.cjwx.titan.crawler.bean.ClrWebUrlBean;
import com.cjwx.titan.crawler.service.WebUrlService;
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
@Api(tags = "网页爬虫-页面管理")
@RestHandler("/webUrl/")
public class WebUrlHandler {

    @Resource
    private WebUrlService webUrlService;

    @RestMethod("list")
    public PageList<ClrWebUrlBean> list(@RequestBody Model model) {
        return webUrlService.getWebUrlList(model.getStart(), model.getSize(), model.getParams(ClrWebUrlBean.class));
    }

    @RestMethod("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return webUrlService.deleteWebUrl(ids);
    }

    public void create(ClrPageBean page) {
        webUrlService.createPage(page);
    }

}
