package com.cjwx.titan.shiro.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.shiro.bean.SysResourceBean;
import com.cjwx.titan.shiro.service.ResourceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 系统管理-资源管理
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@RestController
@RequestMapping(value = "/system/resource/", method = RequestMethod.POST)
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
        return resourceService.updateResource(model.getId(), model.getParams());
    }

    @RequestMapping("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return resourceService.deleteResource(ids);
    }

    @RequestMapping("status")
    public int status(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        boolean status = model.getBoolean("state");
        return resourceService.updateStatus(ids, status);
    }

}
