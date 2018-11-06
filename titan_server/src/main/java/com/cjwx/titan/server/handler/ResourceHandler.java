package com.cjwx.titan.server.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.helper.RibbonClientHelper;
import com.cjwx.titan.server.bean.SysResourceBean;
import com.cjwx.titan.server.service.ResourceService;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@RestHandler("系统管理-资源管理")
@RequestMapping(value = "/resource/", method = RequestMethod.POST)
public class ResourceHandler {

    @Resource
    private ResourceService resourceService;

    @RequestMapping("list")
    public List<SysResourceBean> list() {
        return resourceService.getResourceList();
    }

    @RequestMapping("availableList")
    public List<SysResourceBean> availableList() {
        return resourceService.getResourceList(true);
    }

    @RequestMapping("create")
    public void create(@RequestBody SysResourceBean resource) {
        resourceService.createResource(resource);
    }

    @RequestMapping("edit")
    public int edit(@RequestBody Model model) {
        return resourceService.updateResource(model.getId(), model.getParams(SysResourceBean.class));
    }

    @RequestMapping("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return resourceService.deleteResource(ids);
    }

    @RequestMapping("status")
    public int status(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        boolean status = model.getBoolean("status");
        return resourceService.updateStatus(ids, status);
    }

    @RequestMapping("sync")
    public void sync() {
        List<String> urls = new ArrayList<>();
        Map<String, Long> allMap = new HashMap<>();
        resourceService.getResourceList().forEach(r -> {
            urls.add(r.getUrl());
            allMap.put(r.getResourcecode(), r.getId());
        });
        List<SysResourceBean> add = RibbonClientHelper.getResult("/urlStream", String[].class)
                .stream().flatMap(s -> Arrays.stream(s))
                .filter(url -> !HttpConstant.EXCLUSIONS.contains(url) && !urls.contains(url))
                .map(url -> new UrlParser(allMap, url))
                .filter(UrlParser::isPass)
                .map(UrlParser::getResource)
                .collect(Collectors.toList());
        if (add.size() > 0) {
            resourceService.createResource(add);
        }
    }

    @Data
    class UrlParser {
        private String url;
        private Long parentid;
        private boolean pass = false;
        private String key;
        private String code;
        private String[] tags;

        public UrlParser(Map<String, Long> allMap, String url) {
            this.url = url;
            this.tags = url.split("/");
            int l = this.tags.length;
            if (l > 3) {
                this.key = this.tags[l - 2];
                this.parentid = allMap.get(key.toLowerCase());
                if (this.parentid != null) {
                    this.pass = true;
                    this.code = (key + "_" + this.tags[l - 1]).toUpperCase();
                }
            }
        }

        public SysResourceBean getResource() {
            SysResourceBean resource = new SysResourceBean();
            resource.setParentid(this.parentid);
            resource.setUrl(this.url);
            resource.setType("3");
            resource.setResourcecode(this.code);
            resource.setResourcename(this.code);
            resource.setStatus(true);
            return resource;
        }
    }

}
