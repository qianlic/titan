package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.dto.SysRoleDTO;
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

    @RestMethod("create")
    public ResultDTO<Integer> create(@RequestBody SysRoleDTO role) throws Exception {
        return roleService.createRole(role);
    }

    @RestMethod("edit")
    public ResultDTO<Integer> edit(@RequestBody SysRoleDTO role) throws Exception {
        return roleService.updateRole(role);
    }

    @RestMethod("remove")
    public ResultDTO<Integer> remove(@RequestBody List<Long> ids) {
        return roleService.deleteRole(ids);
    }

    @RestMethod("list")
    public ResultDTO<PageDTO<SysRoleDTO>> list(@RequestBody SysRoleDTO role) throws Exception {
        return roleService.getRoleList(role, role.getStart(), role.getSize());
    }

    @RestMethod("status")
    public ResultDTO<Integer> status(@RequestBody JSONObject param) {
        List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
        boolean status = param.getBoolean("status");
        return roleService.updateStatus(ids, status);
    }

    @RestMethod("availableList")
    public ResultDTO<List<SysRoleDTO>> availableList() throws Exception {
        return roleService.getRoleList();
    }

}
