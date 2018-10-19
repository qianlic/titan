package com.cjwx.titan.server.service;

import com.cjwx.titan.server.bean.SysResourceBean;

import java.util.List;
import java.util.Map;

public interface ResourceService {

    void createResource(SysResourceBean resource);

    void createResource(List<SysResourceBean> resources);

    int deleteResource(List ids);

    int updateResource(int id, Map<String, Object> set);

    int updateStatus(List ids, boolean status);

    List<SysResourceBean> getResourceList();

    List<SysResourceBean> getResourceList(Boolean status);

    List<SysResourceBean> findResourceByIds(String resourceids);

    List<SysResourceBean> findResourceByIds(List<String> resourceids);

}
