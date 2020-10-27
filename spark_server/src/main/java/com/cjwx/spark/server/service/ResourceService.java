package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.entity.SysResource;

import java.util.List;

public interface ResourceService {

    int createResource(SysResource resource);

    int deleteResource(List<Long> ids);

    int updateResource(SysResource resource);

    int updateStatus(List<Long> ids, boolean status);

    List<SysResource> getResourceList();

    List<SysResource> getResourceList(Boolean status);

    List<SysResource> findResourceByIds(String resourceids);

    List<SysResource> findResourceByIds(List<String> resourceids);

}
