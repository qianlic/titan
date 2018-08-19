package com.cjwx.titan.shiro.service.Impl;

import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.shiro.bean.SysResourceBean;
import com.cjwx.titan.shiro.dao.ResourceDao;
import com.cjwx.titan.shiro.service.ResourceService;
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
        resourceDao.createResource(resource);
    }

    @Override
    public int deleteResource(List ids) {
        return resourceDao.deleteResource(ids);
    }

    @Override
    public int updateResource(int id, Map<String, Object> set) {
        return resourceDao.updateResource(id, set);
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return resourceDao.updateStatus(ids, status);
    }

    @Override
    public List<SysResourceBean> getResourceList() {
        return resourceDao.findResourceList();
    }

    @Override
    public List<SysResourceBean> getResourceList(Boolean available) {
        return resourceDao.findResourceList(available);
    }

    @Override
    public List<SysResourceBean> findResourceByIds(String resourceids) {
        return findResourceByIds(StringUtils.stringToList(resourceids));
    }

    @Override
    public List<SysResourceBean> findResourceByIds(List<String> resourceids) {
        return resourceDao.findResourceByIds(resourceids);
    }

}
