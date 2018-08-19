package com.cjwx.titan.shiro.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.shiro.bean.SysRoleBean;
import com.cjwx.titan.shiro.dao.RoleDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoleDaoImpl extends BaseDao implements RoleDao {

    @Override
    public void createRole(SysRoleBean role) {
        this.save(role);
    }

    @Override
    public int deleteRole(List ids) {
        return this.getExecute().table(SysRoleBean.TABLE).in("id", ids).delete();
    }

    @Override
    public int updateRole(int id, Map<String, Object> set) {
        return this.getExecute().table(SysRoleBean.TABLE).set(set).eq("id", id).update();
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return this.getExecute().table(SysRoleBean.TABLE).set("status", status).in("id", ids).update();
    }

    @Override
    public SysRoleBean findRoleByCode(String rolencode) {
        return this.getQuery().from(SysRoleBean.TABLE).eq("rolencode", rolencode).uniqueResult(SysRoleBean.class);
    }

    @Override
    public List<SysRoleBean> findRoleList() {
        return this.getQuery().from(SysRoleBean.TABLE).eq("status", true).list(SysRoleBean.class);
    }

    @Override
    public List<SysRoleBean> findRolesByIds(List<String> ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            return this.getQuery().from(SysRoleBean.TABLE)
                    .eq("status", true)
                    .in("id", ids)
                    .list(SysRoleBean.class);
        }
        return null;
    }

    @Override
    public PageList<SysRoleBean> findRoleList(int start, int size, Map<String, Object> wheres) {
        return this.getQuery().from(SysRoleBean.TABLE).eq(wheres).page(start, size, SysRoleBean.class);
    }

}
