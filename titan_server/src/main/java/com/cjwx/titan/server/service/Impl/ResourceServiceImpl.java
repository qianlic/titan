package com.cjwx.titan.server.service.Impl;

import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.server.bean.SysResourceBean;
import com.cjwx.titan.server.dao.ResourceDao;
import com.cjwx.titan.server.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceDao resourceDao;

    @Override
    public void createResource(SysResourceBean resource) {
        resourceDao.insert(resource);
    }

    @Override
    public void createResource(List<SysResourceBean> resources) {
        resourceDao.batchInsert(resources);
    }

    @Override
    public int deleteResource(List ids) {
        return resourceDao.delete(ids);
    }

    @Override
    public int updateResource(int id, Map<String, Object> set) {
        return resourceDao.update(id, set);
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return resourceDao.update(ids, status);
    }

    @Override
    public List<SysResourceBean> getResourceList() {
        return resourceDao.select();
    }

    @Override
    public List<SysResourceBean> getResourceList(Boolean available) {
        return resourceDao.select(available);
    }

    @Override
    public List<SysResourceBean> findResourceByIds(String resourceids) {
        return findResourceByIds(StringUtils.stringToList(resourceids));
    }

    @Override
    public List<SysResourceBean> findResourceByIds(List<String> resourceids) {
        return resourceDao.select(resourceids);
    }

}
