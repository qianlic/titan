package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysUserEntity;

import java.util.List;

/**
 * Created by CJWX on 2016/4/10.
 */
public interface UserService {

    SysUserEntity createUser(SysUserEntity user);

    SysUserEntity updateUser(SysUserEntity user);

    int deleteUser(List<Long> ids);

    int updateStatus(List<Long> ids, boolean status);

    int updatePassword(List<Long> ids, String password,String salt);

    List<SysUserEntity> getUserList(SysUserEntity user);

    PageList<SysUserEntity> getUserList(int start, int size, SysUserEntity user);

    List<SysUserEntity> findUserByIds(List<Long> ids);

    SysUserEntity findUserByCode(String usercode);

}
