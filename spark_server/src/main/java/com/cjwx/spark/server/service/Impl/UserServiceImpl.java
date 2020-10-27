package com.cjwx.spark.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysUser;
import com.cjwx.spark.engine.repository.UserRepository;
import com.cjwx.spark.server.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CJWX on 2016/4/10.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public int createUser(SysUser user) {
        return userRepository.insert(user);
    }

    @Override
    public int updateUser(SysUser user) {
        return userRepository.updateById(user);
    }

    @Override
    public int deleteUser(List<Long> ids) {
        return userRepository.deleteBatchIds(ids);
    }

    @Override
    public int updateStatus(List<Long> ids, boolean status) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(SysUser::getId, ids);
        wrapper.set(SysUser::getStatus, status);
        return userRepository.update(null, wrapper);
    }

    @Override
    public int updatePassword(List<Long> ids, String password, String salt) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(SysUser::getId, ids);
        wrapper.set(SysUser::getPassword, password);
        wrapper.set(SysUser::getSalt, salt);
        return userRepository.update(null, wrapper);
    }

    @Override
    public List<SysUser> getUserList(SysUser user) {
        QueryWrapper<SysUser> query = new QueryWrapper<>(user);
        return userRepository.selectList(query);
    }

    @Override
    public PageList<SysUser> getUserList(int start, int size, SysUser user) {
        return PageList.of(userRepository, user, start, size);
    }

    @Override
    public List<SysUser> findUserByIds(List<Long> ids) {
        return userRepository.selectBatchIds(ids);
    }

    @Override
    public SysUser findUserByCode(String usercode) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserCode, usercode);
        return userRepository.selectOne(wrapper);
    }

}
