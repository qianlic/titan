package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.model.Model;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysRoleEntity;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.service.RoleService;
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
    public PageList<SysRoleEntity> list(@RequestBody Model<SysRoleEntity> model) {
        return roleService.getRoleList(model.getStart(), model.getSize(), model.getParams());
    }

    @RestMethod("availableList")
    public List<SysRoleEntity> availableList() {
        return roleService.getRoleList();
    }

    @RestMethod("create")
    public void create(@RequestBody SysRoleEntity role) {
        roleService.createRole(role);
    }

    @RestMethod("edit")
    public SysRoleEntity edit(@RequestBody SysRoleEntity role) {
        return roleService.updateRole(role);
    }

    @RestMethod("remove")
    public int remove(@RequestBody List<Long> ids) {
        return roleService.deleteRole(ids);
    }

    @RestMethod("status")
    public int status(@RequestBody JSONObject param) {
        List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
        boolean status = param.getBoolean("status");
        return roleService.updateStatus(ids, status);
    }

}
