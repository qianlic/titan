package com.cjwx.titan.server.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.SysRoleBean;

import java.util.List;
import java.util.Map;

public interface RoleService {

    void createRole(SysRoleBean role);

    int deleteRole(List ids);

    int updateRole(int id, Map<String, Object> set);

    int updateStatus(List ids, boolean status);

    List<SysRoleBean> getRoleList();

    PageList<SysRoleBean> getRoleList(int start, int size, Map<String, Object> wheres);

    List<SysRoleBean> findRolesByIds(String roleids);

}
