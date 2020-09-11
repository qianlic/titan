package com.cjwx.spark.server.service.Impl;

import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysUserEntity;
import com.cjwx.spark.engine.repository.UserRepository;
import com.cjwx.spark.server.service.UserService;
import org.springframework.data.domain.Example;
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
    public SysUserEntity createUser(SysUserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public SysUserEntity updateUser(SysUserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public int deleteUser(List<Long> ids) {
        return userRepository.deleteByIdIn(ids);
    }

    @Override
    public int updateStatus(List<Long> ids, boolean status) {
        return userRepository.updateStatusByIdIn(status, ids);
    }

    @Override
    public int updatePassword(List<Long> ids, String password, String salt) {
        return userRepository.updatePasswordAndSaltByIdIn(password, salt, ids);
    }

    @Override
    public List<SysUserEntity> getUserList(SysUserEntity user) {
        return userRepository.findAll(Example.of(user));
    }

    @Override
    public PageList<SysUserEntity> getUserList(int start, int size, SysUserEntity user) {
        return PageList.of(userRepository, user, start, size);
    }

    @Override
    public List<SysUserEntity> findUserByIds(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public SysUserEntity findUserByCode(String usercode) {
        return userRepository.findByUserCode(usercode);
    }

}
