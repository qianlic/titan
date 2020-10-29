package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.server.dto.SysResourceDTO;

import java.util.List;

public interface ResourceService {

    ResultDTO<Integer> createResource(SysResourceDTO resource) throws Exception;

    ResultDTO<Integer> updateResource(SysResourceDTO resource) throws Exception;

    ResultDTO<Integer> deleteResource(List<Long> ids);

    ResultDTO<Integer> updateStatus(List<Long> ids, boolean status);

    ResultDTO<List<SysResourceDTO>> getResourceList() throws Exception;

    ResultDTO<List<SysResourceDTO>> getResourceList(Boolean available) throws Exception;

    ResultDTO<List<SysResourceDTO>> findResourceByIds(List<Long> ids) throws Exception;

}
