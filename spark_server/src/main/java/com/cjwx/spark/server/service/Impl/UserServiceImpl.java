package com.cjwx.spark.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.entity.SysUser;
import com.cjwx.spark.engine.repository.UserRepository;
import com.cjwx.spark.engine.util.MapperUtils;
import com.cjwx.spark.engine.util.ObjectUtils;
import com.cjwx.spark.server.dto.SysUserDTO;
import com.cjwx.spark.server.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户服务
 * @Author: qian li
 * @Date: 2020/10/28 15:49
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public ResultDTO<Integer> createUser(SysUserDTO user) throws Exception {

        return MapperUtils.insert(userRepository, ObjectUtils.convert(user, SysUser.class));
    }

    @Override
    public ResultDTO<Integer> updateUser(SysUserDTO user) throws Exception {
        return MapperUtils.update(userRepository, ObjectUtils.convert(user, SysUser.class));
    }

    @Override
    public ResultDTO<Integer> deleteUser(List<Long> ids) {
        return MapperUtils.delete(userRepository, ids);
    }

    @Override
    public ResultDTO<List<SysUserDTO>> getUserList(SysUserDTO user) throws Exception {
        return MapperUtils.list(userRepository, ObjectUtils.convert(user, SysUser.class), SysUserDTO.class);
    }

    @Override
    public ResultDTO<PageDTO<SysUserDTO>> getUserList(SysUserDTO user, int start, int size) throws Exception {
        return MapperUtils.pageList(userRepository, ObjectUtils.convert(user, SysUser.class), start, size, SysUserDTO.class);
    }

    @Override
    public ResultDTO<Integer> updateStatus(List<Long> ids, boolean status) {
        return MapperUtils.update(userRepository, new LambdaUpdateWrapper<SysUser>()
                .in(SysUser::getId, ids)
                .set(SysUser::getStatus, status));
    }

    @Override
    public ResultDTO<Integer> updatePassword(List<Long> ids, String password, String salt) {
        return MapperUtils.update(userRepository, new LambdaUpdateWrapper<SysUser>()
                .in(SysUser::getId, ids)
                .set(SysUser::getPassword, password)
                .set(SysUser::getSalt, salt));
    }

    @Override
    public ResultDTO<SysUserDTO> findUserByCode(String userCode) throws Exception {
        return MapperUtils.select(userRepository, new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserCode, userCode), SysUserDTO.class);
    }

}
