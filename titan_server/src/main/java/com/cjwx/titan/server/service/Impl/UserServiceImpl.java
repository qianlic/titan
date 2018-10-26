package com.cjwx.titan.server.service.Impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.SysUserBean;
import com.cjwx.titan.server.dao.UserDao;
import com.cjwx.titan.server.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
        userDao.insert(user);
    }

    @Override
    public int updateUser(int id, Map<String, Object> set) {
        return userDao.update(id, set);
    }

    @Override
    public int deleteUser(List ids) {
        return userDao.delete(ids);
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return userDao.update(ids, status);
    }

    @Override
    public int updatePassword(List ids, String password, String salt) {
        return userDao.update(ids, password, salt);
    }

    @Override
    public List<SysUserBean> getUserList(Map<String, Object> wheres) {
        return userDao.select( wheres);
    }

    @Override
    public PageList<SysUserBean> getUserList(int start, int size, Map<String, Object> wheres) {
        return userDao.select(start, size, wheres);
    }

    @Override
    public List<SysUserBean> findUserByIds(List ids) {
        return userDao.select(ids);
    }

    @Override
    public SysUserBean findUserByCode(String usercode) {
        return userDao.select(usercode);
    }

}
