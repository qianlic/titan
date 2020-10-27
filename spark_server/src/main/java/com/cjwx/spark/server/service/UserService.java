package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysUser;

import java.util.List;

/**
 * Created by CJWX on 2016/4/10.
 */
public interface UserService {

    int createUser(SysUser user);

    int updateUser(SysUser user);

    int deleteUser(List<Long> ids);

    int updateStatus(List<Long> ids, boolean status);

    int updatePassword(List<Long> ids, String password,String salt);

    List<SysUser> getUserList(SysUser user);

    PageList<SysUser> getUserList(int start, int size, SysUser user);

    List<SysUser> findUserByIds(List<Long> ids);

    SysUser findUserByCode(String usercode);

}
