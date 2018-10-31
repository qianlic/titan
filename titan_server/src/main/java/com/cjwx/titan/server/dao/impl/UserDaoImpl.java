package com.cjwx.titan.server.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.SysUserBean;
import com.cjwx.titan.server.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

    private static final String CUSTOM_COLUMS = "id,usercode,username,sex,birthday,mobile,email,imgurl,lastlogintime,roleids,status";

    @Override
    public int delete(List ids) {
        return this.getExecute(SysUserBean.class).in("id", ids).delete();
    }

    @Override
    public int update(int id, Map<String, Object> set) {
        return this.getExecute(SysUserBean.class).set(set).eq("id", id).update();
    }

    @Override
    public int update(List ids, boolean status) {
        return this.getExecute(SysUserBean.class).set("status", status).in("id", ids).update();
    }

    @Override
    public int update(List ids, String password, String salt) {
        return this.getExecute(SysUserBean.class).set("password", password).set("salt", salt).in("id", ids).update();
    }

    @Override
    public List<SysUserBean> select(Map<String, Object> where) {
        return this.getQuery(SysUserBean.class).select(CUSTOM_COLUMS).eq(where).list();
    }

    @Override
    public PageList<SysUserBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(SysUserBean.class).select(CUSTOM_COLUMS).eq(where).page(start, size);
    }

    @Override
    public List<SysUserBean> select(List ids) {
        return this.getQuery(SysUserBean.class).in("id", ids).list();
    }

    @Override
    public SysUserBean select(String usercode) {
        return this.getQuery(SysUserBean.class).eq("usercode", usercode).uniqueResult();
    }

}
