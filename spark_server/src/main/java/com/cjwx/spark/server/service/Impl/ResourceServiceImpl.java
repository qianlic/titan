package com.cjwx.spark.server.service.Impl;

import com.cjwx.spark.engine.entity.SysResourceEntity;
import com.cjwx.spark.engine.repository.ResourceRepository;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.server.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceRepository resourceRepository;

    @Override
    public SysResourceEntity createResource(SysResourceEntity resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public List<SysResourceEntity> createResource(List<SysResourceEntity> resources) {
        return resourceRepository.saveAll(resources);
    }

    @Override
    public int deleteResource(List<Long> ids) {
        return resourceRepository.deleteByIdIn(ids);
    }

    @Override
    public SysResourceEntity updateResource(SysResourceEntity resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public int updateStatus(List<Long> ids, boolean status) {
        return resourceRepository.updateStatusByIdIn(status, ids);
    }

    @Override
    public List<SysResourceEntity> getResourceList() {
        return resourceRepository.findAll();
    }

    @Override
    public List<SysResourceEntity> getResourceList(Boolean available) {
        return resourceRepository.findByStatus(available);
    }

    @Override
    public List<SysResourceEntity> findResourceByIds(String resourceids) {
        return findResourceByIds(StringUtils.stringToList(resourceids));
    }

    @Override
    public List<SysResourceEntity> findResourceByIds(List<String> resourceids) {
        return resourceRepository.findAllById(resourceids.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList()));
    }

}
