package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.entity.SysResourceEntity;

import java.util.List;

public interface ResourceService {

    SysResourceEntity createResource(SysResourceEntity resource);

    List<SysResourceEntity> createResource(List<SysResourceEntity> resources);

    int deleteResource(List<Long> ids);

    SysResourceEntity updateResource(SysResourceEntity resource);

    int updateStatus(List<Long> ids, boolean status);

    List<SysResourceEntity> getResourceList();

    List<SysResourceEntity> getResourceList(Boolean status);

    List<SysResourceEntity> findResourceByIds(String resourceids);

    List<SysResourceEntity> findResourceByIds(List<String> resourceids);

}
