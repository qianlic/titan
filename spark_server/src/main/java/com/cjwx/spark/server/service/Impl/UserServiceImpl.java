package com.cjwx.spark.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.entity.SysUser;
import com.cjwx.spark.engine.repository.UserRepository;
import com.cjwx.spark.engine.util.MapperUtils;
import com.cjwx.spark.server.dto.SysUserDTO;
import com.cjwx.spark.server.service.UserService;
import org.springframework.beans.BeanUtils;
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
    public ResultDTO<Integer> createUser(SysUserDTO dto) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(dto, entity);
        return MapperUtils.insert(userRepository, entity);
    }

    @Override
    public ResultDTO<Integer> updateUser(SysUserDTO dto) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(dto, entity);
        return MapperUtils.update(userRepository, entity);
    }

    @Override
    public ResultDTO<Integer> deleteUser(List<Long> ids) {
        return MapperUtils.delete(userRepository, ids);
    }

    @Override
    public ResultDTO<Integer> updateStatus(List<Long> ids, boolean status) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(SysUser::getId, ids);
        wrapper.set(SysUser::getStatus, status);
        return MapperUtils.update(userRepository, wrapper);
    }

    @Override
    public ResultDTO<Integer> updatePassword(List<Long> ids, String password, String salt) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(SysUser::getId, ids);
        wrapper.set(SysUser::getPassword, password);
        wrapper.set(SysUser::getSalt, salt);
        return MapperUtils.update(userRepository, wrapper);
    }

    @Override
    public ResultDTO<List<SysUserDTO>> getUserList(SysUserDTO dto) throws Exception {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(dto, entity);
        return MapperUtils.list(userRepository, entity, SysUserDTO.class);
    }

    @Override
    public ResultDTO<PageDTO<SysUserDTO>> getUserList(SysUserDTO dto, int start, int size) throws Exception {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(dto, entity);
        return MapperUtils.pageList(userRepository, entity, start, size, SysUserDTO.class);
    }

    @Override
    public ResultDTO<SysUserDTO> findUserByCode(String userCode) throws Exception {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserCode, userCode);
        return MapperUtils.select(userRepository, wrapper, SysUserDTO.class);
    }

}
