package com.cjwx.titan.server.service.Impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.SysRoleBean;
import com.cjwx.titan.server.dao.RoleDao;
import com.cjwx.titan.server.service.RoleService;
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
        roleDao.insert(role);
    }

    @Override
    public int deleteRole(List ids) {
        return roleDao.delete(ids);
    }

    @Override
    public int updateRole(int id, Map<String, Object> set) {
        return roleDao.update(id, set);
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return roleDao.update(ids, status);
    }

    @Override
    public List<SysRoleBean> getRoleList() {
        return roleDao.select();
    }

    @Override
    public PageList<SysRoleBean> getRoleList(int start, int size, Map<String, Object> wheres) {
        return roleDao.select(start, size, wheres);
    }

    @Override
    public List<SysRoleBean> findRolesByIds(String roleids) {
        return roleDao.select(StringUtils.stringToList(roleids));
    }

}
