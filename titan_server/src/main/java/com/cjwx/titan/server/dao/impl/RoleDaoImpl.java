package com.cjwx.titan.server.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.server.bean.SysRoleBean;
import com.cjwx.titan.server.dao.RoleDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoleDaoImpl extends BaseDao implements RoleDao {

    @Override
    public int delete(List ids) {
        return this.getExecute(SysRoleBean.class).in("id", ids).delete();
    }

    @Override
    public int update(int id, Map<String, Object> set) {
        return this.getExecute(SysRoleBean.class).set(set).eq("id", id).update();
    }

    @Override
    public int update(List ids, boolean status) {
        return this.getExecute(SysRoleBean.class).set("status", status).in("id", ids).update();
    }

    @Override
    public List<SysRoleBean> select() {
        return this.getQuery(SysRoleBean.class).eq("status", true).list();
    }

    @Override
    public List<SysRoleBean> select(List<String> ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            return this.getQuery(SysRoleBean.class).eq("status", true).in("id", ids).list();
        }
        return null;
    }

    @Override
    public PageList<SysRoleBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(SysRoleBean.class).eq(where).page(start, size);
    }

}
