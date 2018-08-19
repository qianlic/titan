package com.cjwx.titan.shiro.service.Impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.shiro.bean.SysRoleBean;
import com.cjwx.titan.shiro.dao.RoleDao;
import com.cjwx.titan.shiro.service.RoleService;
import com.cjwx.titan.engine.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public void createRole(SysRoleBean role) {
        roleDao.createRole(role);
    }

    @Override
    public int deleteRole(List ids) {
        return roleDao.deleteRole(ids);
    }

    @Override
    public int updateRole(int id, Map<String, Object> set) {
        return roleDao.updateRole(id, set);
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return roleDao.updateStatus(ids, status);
    }

    @Override
    public List<SysRoleBean> getRoleList() {
        return roleDao.findRoleList();
    }

    @Override
    public PageList<SysRoleBean> getRoleList(int start, int size, Map<String, Object> wheres) {
        return roleDao.findRoleList(start, size, wheres);
    }

    @Override
    public List<SysRoleBean> findRolesByIds(String roleids) {
        return roleDao.findRolesByIds(StringUtils.stringToList(roleids));
    }

}
