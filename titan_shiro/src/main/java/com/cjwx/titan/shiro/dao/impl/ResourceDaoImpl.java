package com.cjwx.titan.shiro.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.shiro.bean.SysResourceBean;
import com.cjwx.titan.shiro.dao.ResourceDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ResourceDaoImpl extends BaseDao implements ResourceDao {

    @Override
    public void createResource(SysResourceBean resource) {
        this.save(resource);
    }

    @Override
    public int deleteResource(List ids) {
        return this.getExecute().table(SysResourceBean.TABLE).in("id", ids).delete();
    }

    @Override
    public int updateResource(int id, Map<String, Object> set) {
        return this.getExecute().table(SysResourceBean.TABLE).set(set).eq("id", id).update();
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return this.getExecute().table(SysResourceBean.TABLE).set("status", status).in("id", ids).update();
    }

    @Override
    public List<SysResourceBean> findResourceList() {
        return this.getQuery().from(SysResourceBean.TABLE).list(SysResourceBean.class);
    }

    @Override
    public List<SysResourceBean> findResourceList(boolean available) {
        return this.getQuery().from(SysResourceBean.TABLE)
                .eq("status", available)
                .list(SysResourceBean.class);
    }

    @Override
    public List<SysResourceBean> findResourceByIds(List<String> ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            return this.getQuery().from(SysResourceBean.TABLE)
                    .eq("status", true)
                    .in("id", ids)
                    .list(SysResourceBean.class);
        }
        return null;
    }

}
