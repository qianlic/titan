package com.cjwx.titan.shiro.service.Impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.shiro.bean.SysUserBean;
import com.cjwx.titan.shiro.cache.RedisCache;
import com.cjwx.titan.shiro.dao.UserDao;
import com.cjwx.titan.shiro.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by CJWX on 2016/4/10.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void createUser(SysUserBean user) {
        userDao.createUser(user);
    }

    @Override
    public int updateUser(int id, Map<String, Object> set) {
        this.clearCache(Arrays.asList(id));
        return userDao.updateUser(id, set);
    }

    @Override
    public int deleteUser(List ids) {
        this.clearCache(ids);
        return userDao.deleteUser(ids);
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        this.clearCache(ids);
        return userDao.updateStatus(ids, status);
    }

    @Override
    public int updatePassword(List ids, String password,String salt){
        this.clearCache(ids);
        return userDao.updatePassword(ids, password,salt);
    }

    @Override
    public PageList<SysUserBean> getUserList(int start, int size, Map<String, Object> wheres) {
        return userDao.findUserList(start, size, wheres);
    }

    @Override
    public List<SysUserBean> findUserByIds(List ids) {
        return userDao.findUserByIds(ids);
    }

    @Override
    public SysUserBean findUserByCode(String usercode) {
        return userDao.findUserByCode(usercode);
    }

    private void clearCache(List ids) {
        List<SysUserBean> users = this.findUserByIds(ids);
        users.forEach(user -> RedisCache.removeAuthenticationCache(user.getUsercode()));
    }

}
