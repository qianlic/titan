package com.cjwx.titan.shiro.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.shiro.bean.SysUserBean;

import java.util.List;
import java.util.Map;

/**
 * Created by CJWX on 2016/4/10.
 */
public interface UserDao {

    void createUser(SysUserBean user);

    int deleteUser(List ids);

    int updateUser(int id, Map<String, Object> set);

    int updateStatus(List ids, boolean status);

    int updatePassword(List ids, String password, String salt);

    PageList<SysUserBean> findUserList(int start, int size, Map<String, Object> wheres);

    List<SysUserBean> findUserByIds(List ids);

    SysUserBean findUserByCode(String usercode);

}
