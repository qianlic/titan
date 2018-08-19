package com.cjwx.titan.shiro.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.shiro.bean.SysRoleBean;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    void createRole(SysRoleBean role);

    int deleteRole(List ids);

    int updateRole(int id, Map<String, Object> set);

    int updateStatus(List ids, boolean status);

    SysRoleBean findRoleByCode(String rolecode);

    List<SysRoleBean> findRoleList();

    List<SysRoleBean> findRolesByIds(List<String> ids);

    PageList<SysRoleBean> findRoleList(int start, int size, Map<String, Object> wheres);

}
