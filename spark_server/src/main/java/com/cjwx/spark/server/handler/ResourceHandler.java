package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.entity.SysResource;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.engine.web.helper.RibbonClientHelper;
import com.cjwx.spark.server.dto.SysResourceDTO;
import com.cjwx.spark.server.service.ResourceService;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@Api(tags = "系统管理-资源管理")
@RestHandler("/resource/")
public class ResourceHandler {

    @Resource
    private ResourceService resourceService;

    @RestMethod("create")
    public ResultDTO<Integer> create(@RequestBody SysResourceDTO resource) throws Exception {
        return resourceService.createResource(resource);
    }

    @RestMethod("edit")
    public ResultDTO<Integer> edit(@RequestBody SysResourceDTO resource) throws Exception {
        return resourceService.updateResource(resource);
    }

    @RestMethod("remove")
    public ResultDTO<Integer> remove(@RequestBody List<Long> ids) {
        return resourceService.deleteResource(ids);
    }

    @RestMethod("list")
    public ResultDTO<List<SysResourceDTO>> list() throws Exception {
        return resourceService.getResourceList();
    }

    @RestMethod("availableList")
    public ResultDTO<List<SysResourceDTO>> availableList() throws Exception {
        return resourceService.getResourceList(true);
    }

    @RestMethod("status")
    public ResultDTO<Integer> status(@RequestBody JSONObject param) {
        List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
        boolean status = param.getBoolean("status");
        return resourceService.updateStatus(ids, status);
    }

    @RestMethod("sync")
    public void sync() throws Exception {
        List<String> urls = new ArrayList<>();
        Map<String, Long> allMap = new HashMap<>();
        resourceService.getResourceList().getData().forEach(r -> {
            urls.add(r.getUrl());
            allMap.put(r.getResourceCode(), r.getId());
        });
        List<SysResource> add = RibbonClientHelper.doPost("/urlStream", String[].class)
                .stream().flatMap(Arrays::stream)
                .filter(url -> !AppConstant.EXCLUSIONS.contains(url) && !urls.contains(url))
                .map(url -> new UrlParser(allMap, url))
                .filter(UrlParser::isPass)
                .map(UrlParser::getResource)
                .collect(Collectors.toList());
        if (add.size() > 0) {
            //resourceService.createResource(add);
        }
    }

    @Data
    static class UrlParser {
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

        public SysResource getResource() {
            SysResource resource = new SysResource();
            //resource.setParentid(this.parentid);
            resource.setUrl(this.url);
            resource.setType("3");
            resource.setResourceCode(this.code);
            resource.setResourceName(this.code);
            resource.setStatus(true);
            return resource;
        }
    }

}
