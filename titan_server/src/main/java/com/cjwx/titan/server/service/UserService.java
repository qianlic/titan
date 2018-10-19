package com.cjwx.titan.server.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.SysUserBean;

import java.util.List;
import java.util.Map;

/**
 * Created by CJWX on 2016/4/10.
 */
public interface UserService {

    void createUser(SysUserBean user);

    int updateUser(int id, Map<String, Object> set);

    int deleteUser(List ids);

    int updateStatus(List ids, boolean status);

    int updatePassword(List ids, String password,String salt);

    PageList<SysUserBean> getUserList(int start, int size, Map<String, Object> wheres);

    List<SysUserBean> findUserByIds(List ids);

    SysUserBean findUserByCode(String usercode);

}
