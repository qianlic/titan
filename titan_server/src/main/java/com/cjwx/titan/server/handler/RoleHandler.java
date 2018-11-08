package com.cjwx.titan.server.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.server.bean.SysRoleBean;
import com.cjwx.titan.server.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@Api(tags = "系统管理-角色管理")
@RestHandler("/role/")
public class RoleHandler {

    @Resource
    private RoleService roleService;

    @RestMethod("list")
    public PageList<SysRoleBean> list(@RequestBody Model model) {
        return roleService.getRoleList(model.getStart(), model.getSize(), model.getParams(SysRoleBean.class));
    }

    @RestMethod("availableList")
    public List<SysRoleBean> availableList() {
        return roleService.getRoleList();
    }

    @RestMethod("create")
    public void create(@RequestBody SysRoleBean role) {
        roleService.createRole(role);
    }

    @RestMethod("edit")
    public int edit(@RequestBody Model model) {
        return roleService.updateRole(model.getId(), model.getParams(SysRoleBean.class));
    }

    @RestMethod("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return roleService.deleteRole(ids);
    }

    @RestMethod("status")
    public int status(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        boolean status = model.getBoolean("status");
        return roleService.updateStatus(ids, status);
    }

}
