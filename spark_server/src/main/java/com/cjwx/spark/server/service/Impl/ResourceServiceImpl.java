package com.cjwx.spark.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cjwx.spark.engine.entity.SysResource;
import com.cjwx.spark.engine.repository.ResourceRepository;
import com.cjwx.spark.engine.util.StringUtils;
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
    public int createResource(SysResource resource) {
        return resourceRepository.insert(resource);
    }

    @Override
    public int deleteResource(List<Long> ids) {
        return resourceRepository.deleteBatchIds(ids);
    }

    @Override
    public int updateResource(SysResource resource) {
        return resourceRepository.updateById(resource);
    }

    @Override
    public int updateStatus(List<Long> ids, boolean status) {
        LambdaUpdateWrapper<SysResource> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(SysResource::getId, ids);
        wrapper.set(SysResource::getStatus, status);
        return resourceRepository.update(null, wrapper);
    }

    @Override
    public List<SysResource> getResourceList() {
        return resourceRepository.selectList(null);
    }

    @Override
    public List<SysResource> getResourceList(Boolean available) {
        LambdaQueryWrapper<SysResource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysResource::getStatus, available);
        return resourceRepository.selectList(wrapper);
    }

    @Override
    public List<SysResource> findResourceByIds(String resourceids) {
        return findResourceByIds(StringUtils.stringToList(resourceids));
    }

    @Override
    public List<SysResource> findResourceByIds(List<String> resourceids) {
        return resourceRepository.selectBatchIds(resourceids);
    }

}
