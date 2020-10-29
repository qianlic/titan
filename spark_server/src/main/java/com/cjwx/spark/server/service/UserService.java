package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.server.dto.SysUserDTO;

import java.util.List;

/**
 * Created by CJWX on 2016/4/10.
 */
public interface UserService {

    ResultDTO<Integer> createUser(SysUserDTO user) throws Exception;

    ResultDTO<Integer> updateUser(SysUserDTO user) throws Exception;

    ResultDTO<Integer> deleteUser(List<Long> ids);

    ResultDTO<Integer> updateStatus(List<Long> ids, boolean status);

    ResultDTO<Integer> updatePassword(List<Long> ids, String password, String salt);

    ResultDTO<List<SysUserDTO>> getUserList(SysUserDTO user) throws Exception;

    ResultDTO<PageDTO<SysUserDTO>> getUserList(SysUserDTO user, int start, int size) throws Exception;

    ResultDTO<SysUserDTO> findUserByCode(String userCode) throws Exception;

}
