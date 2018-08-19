package com.cjwx.titan.shiro.dao;

import com.cjwx.titan.shiro.bean.SysResourceBean;

import java.util.List;
import java.util.Map;

public interface ResourceDao {

    void createResource(SysResourceBean resource);

    int deleteResource(List ids);

    int updateResource(int id, Map<String, Object> set);

    int updateStatus(List ids, boolean status);

    List<SysResourceBean> findResourceList();

    List<SysResourceBean> findResourceList(boolean available);

    List<SysResourceBean> findResourceByIds(List<String> ids);

}
