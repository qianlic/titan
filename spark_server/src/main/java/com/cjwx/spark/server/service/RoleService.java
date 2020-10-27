package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysRole;

import java.util.List;

public interface RoleService {

    int createRole(SysRole role);

    int deleteRole(List<Long> ids);

    int updateRole(SysRole role);

    int updateStatus(List<Long> ids, boolean status);

    List<SysRole> getRoleList();

    PageList<SysRole> getRoleList(int start, int size, SysRole role);

    List<SysRole> findRolesByIds(String roleids);

}
