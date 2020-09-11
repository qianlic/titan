package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysRoleEntity;

import java.util.List;

public interface RoleService {

    SysRoleEntity createRole(SysRoleEntity role);

    int deleteRole(List<Long> ids);

    SysRoleEntity updateRole(SysRoleEntity role);

    int updateStatus(List<Long> ids, boolean status);

    List<SysRoleEntity> getRoleList();

    PageList<SysRoleEntity> getRoleList(int start, int size, SysRoleEntity role);

    List<SysRoleEntity> findRolesByIds(String roleids);

}
