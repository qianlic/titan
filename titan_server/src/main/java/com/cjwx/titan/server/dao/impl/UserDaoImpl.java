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

    @Override
    public int delete(List ids) {
        return this.getExecute().table(SysUserBean.TABLE).in("id", ids).delete();
    }

    @Override
    public int update(int id, Map<String, Object> set) {
        return this.getExecute().table(SysUserBean.TABLE).set(set).eq("id", id).update();
    }

    @Override
    public int update(List ids, boolean status) {
        return this.getExecute().table(SysUserBean.TABLE).set("status", status).in("id", ids).update();
    }

    @Override
    public int update(List ids, String password, String salt) {
        return this.getExecute().table(SysUserBean.TABLE).set("password", password)
                .set("salt", salt).in("id", ids).update();
    }

    @Override
    public PageList<SysUserBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery().select("id,usercode,username,sex,birthday,mobile,email,imgurl,lastlogintime,roleids,status")
                .from(SysUserBean.TABLE).eq(where).page(start, size, SysUserBean.class);
    }

    @Override
    public List<SysUserBean> select(List ids) {
        return this.getQuery().from(SysUserBean.TABLE).in("id", ids).list(SysUserBean.class);
    }

    @Override
    public SysUserBean select(String usercode) {
        return this.getQuery().query(SysUserBean.TABLE, "usercode", usercode).uniqueResult(SysUserBean.class);
    }

}
