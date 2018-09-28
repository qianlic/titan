package com.cjwx.titan.shiro.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.shiro.bean.SysUserBean;

import java.util.List;
import java.util.Map;

public interface UserDao {

    long insert(Object bean);

    int delete(List ids);

    int update(int id, Map<String, Object> set);

    int update(List ids, boolean status);

    int update(List ids, String password, String salt);

    PageList<SysUserBean> select(int start, int size, Map<String, Object> where);

    List<SysUserBean> select(List ids);

    SysUserBean select(String usercode);

}
