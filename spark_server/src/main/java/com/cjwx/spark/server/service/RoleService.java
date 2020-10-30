package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.server.dto.SysRoleDTO;

import java.util.List;

public interface RoleService {

    ResultDTO<Integer> createRole(SysRoleDTO role) throws Exception;

    ResultDTO<Integer> updateRole(SysRoleDTO role) throws Exception;

    ResultDTO<Integer> deleteRole(List<Long> ids);

    ResultDTO<Integer> updateStatus(List<Long> ids, boolean status);

    ResultDTO<List<SysRoleDTO>> getRoleList() throws Exception;

    ResultDTO<PageDTO<SysRoleDTO>> getRoleList(SysRoleDTO role, int start, int size) throws Exception;

    ResultDTO<List<SysRoleDTO>> findByUserId(Long userId) throws Exception;

}
