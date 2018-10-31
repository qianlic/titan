package com.cjwx.titan.server.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.server.bean.SysResourceBean;
import com.cjwx.titan.server.dao.ResourceDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ResourceDaoImpl extends BaseDao implements ResourceDao {

    @Override
    public int delete(List ids) {
        return this.getExecute(SysResourceBean.class).in("id", ids).delete();
    }

    @Override
    public int update(int id, Map<String, Object> set) {
        return this.getExecute(SysResourceBean.class).set(set).eq("id", id).update();
    }

    @Override
    public int update(List ids, boolean status) {
        return this.getExecute(SysResourceBean.class).set("status", status).in("id", ids).update();
    }

    @Override
    public List<SysResourceBean> select() {
        return this.getQuery(SysResourceBean.class).list();
    }

    @Override
    public List<SysResourceBean> select(boolean status) {
        return this.getQuery(SysResourceBean.class).eq("status", status).list();
    }

    @Override
    public List<SysResourceBean> select(List<String> ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            return this.getQuery(SysResourceBean.class).eq("status", true).in("id", ids).list();
        }
        return null;
    }

}
