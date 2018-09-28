package com.cjwx.titan.shiro.dao;

import com.cjwx.titan.shiro.bean.SysResourceBean;

import java.util.List;
import java.util.Map;

public interface ResourceDao {

    long insert(Object bean);

    int delete(List ids);

    int update(int id, Map<String, Object> set);

    int update(List ids, boolean status);

    List<SysResourceBean> select();

    List<SysResourceBean> select(boolean status);

    List<SysResourceBean> select(List<String> ids);

}
