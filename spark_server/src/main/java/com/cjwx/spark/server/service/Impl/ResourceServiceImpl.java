package com.cjwx.spark.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.entity.SysResource;
import com.cjwx.spark.engine.repository.ResourceRepository;
import com.cjwx.spark.engine.util.MapperUtils;
import com.cjwx.spark.engine.util.ObjectUtils;
import com.cjwx.spark.server.dto.SysResourceDTO;
import com.cjwx.spark.server.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceRepository resourceRepository;

    @Override
    public ResultDTO<Integer> createResource(SysResourceDTO resource) throws Exception {
        return MapperUtils.insert(resourceRepository, ObjectUtils.convert(resource, SysResource.class));
    }

    @Override
    public ResultDTO<Integer> updateResource(SysResourceDTO resource) throws Exception {
        return MapperUtils.update(resourceRepository, ObjectUtils.convert(resource, SysResource.class));
    }

    @Override
    public ResultDTO<Integer> deleteResource(List<Long> ids) {
        return MapperUtils.delete(resourceRepository, ids);
    }

    @Override
    public ResultDTO<List<SysResourceDTO>> getResourceList() throws Exception {
        return MapperUtils.list(resourceRepository, SysResourceDTO.class);
    }

    @Override
    public ResultDTO<List<SysResourceDTO>> getResourceList(Boolean status) throws Exception {
        return MapperUtils.list(resourceRepository, new LambdaQueryWrapper<SysResource>()
                .eq(SysResource::getStatus, status), SysResourceDTO.class);
    }

    @Override
    public ResultDTO<Integer> updateStatus(List<Long> ids, boolean status) {
        return MapperUtils.update(resourceRepository, new LambdaUpdateWrapper<SysResource>()
                .in(SysResource::getId, ids)
                .set(SysResource::getStatus, status));
    }

    @Override
    public ResultDTO<List<SysResourceDTO>> findResourceByIds(List<Long> ids) throws Exception {
        return MapperUtils.list(resourceRepository, ids, SysResourceDTO.class);
    }

}
