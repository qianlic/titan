package com.cjwx.titan.server.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.SysRoleBean;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    void insert(Object bean);

    int delete(List ids);

    int update(int id, Map<String, Object> set);

    int update(List ids, boolean status);

    List<SysRoleBean> select();

    List<SysRoleBean> select(List<String> ids);

    PageList<SysRoleBean> select(int start, int size, Map<String, Object> where);

}
