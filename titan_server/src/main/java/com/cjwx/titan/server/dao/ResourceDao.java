package com.cjwx.titan.server.dao;

import com.cjwx.titan.server.bean.SysResourceBean;

import java.util.List;
import java.util.Map;

public interface ResourceDao {

    void insert(Object bean);

    void batchInsert(List beans);

    int delete(List ids);

    int update(int id, Map<String, Object> set);

    int update(List ids, boolean status);

    List<SysResourceBean> select();

    List<SysResourceBean> select(boolean status);

    List<SysResourceBean> select(List<String> ids);

}
