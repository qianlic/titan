package com.cjwx.titan.shiro.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.shiro.bean.SysUserBean;
import com.cjwx.titan.shiro.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by CJWX on 2016/4/10.
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public void createUser(SysUserBean user) {
        this.save(user);
    }

    @Override
    public int deleteUser(List ids) {
        return this.getExecute().table(SysUserBean.TABLE).in("id", ids).delete();
    }

    @Override
    public int updateUser(int id, Map<String, Object> set) {
        return this.getExecute().table(SysUserBean.TABLE).set(set).eq("id", id).update();
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return this.getExecute().table(SysUserBean.TABLE).set("status", status).in("id", ids).update();
    }

    @Override
    public int updatePassword(List ids, String password, String salt) {
        return this.getExecute().table(SysUserBean.TABLE).set("password", password)
                .set("salt", salt).in("id", ids).update();
    }

    @Override
    public PageList<SysUserBean> findUserList(int start, int size, Map<String, Object> wheres) {
        return this.getQuery().select("id,usercode,username,sex,birthday,mobile,email,lastlogintime,roleids,status")
                .from(SysUserBean.TABLE).eq(wheres).page(start, size, SysUserBean.class);
    }

    @Override
    public List<SysUserBean> findUserByIds(List ids) {
        return this.getQuery().from(SysUserBean.TABLE).in("id", ids).list(SysUserBean.class);
    }

    @Override
    public SysUserBean findUserByCode(String usercode) {
        return this.getQuery().query(SysUserBean.TABLE, "usercode", usercode).uniqueResult(SysUserBean.class);
    }

}
