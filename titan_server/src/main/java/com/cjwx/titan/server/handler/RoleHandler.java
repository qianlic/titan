package com.cjwx.titan.server.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.server.bean.SysRoleBean;
import com.cjwx.titan.server.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@RestHandler("系统管理-角色管理")
@RequestMapping(value = "/role/", method = RequestMethod.POST)
public class RoleHandler {

    @Resource
    private RoleService roleService;

    @RequestMapping("list")
    public PageList<SysRoleBean> list(@RequestBody Model model) {
        return roleService.getRoleList(model.getStart(), model.getSize(), model.getParams(SysRoleBean.class));
    }

    @RequestMapping("availableList")
    public List<SysRoleBean> availableList() {
        return roleService.getRoleList();
    }

    @RequestMapping("create")
    public void create(@RequestBody SysRoleBean role) {
        roleService.createRole(role);
    }

    @RequestMapping("edit")
    public int edit(@RequestBody Model model) {
        return roleService.updateRole(model.getId(), model.getParams(SysRoleBean.class));
    }

    @RequestMapping("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return roleService.deleteRole(ids);
    }

    @RequestMapping("status")
    public int status(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        boolean status = model.getBoolean("status");
        return roleService.updateStatus(ids, status);
    }

}
