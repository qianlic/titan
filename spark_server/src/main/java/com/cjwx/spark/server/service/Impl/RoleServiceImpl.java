package com.cjwx.spark.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysRole;
import com.cjwx.spark.engine.repository.RoleRepository;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.server.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public int createRole(SysRole role) {
        return roleRepository.insert(role);
    }

    @Override
    public int deleteRole(List<Long> ids) {
        return roleRepository.deleteBatchIds(ids);
    }

    @Override
    public int updateRole(SysRole role) {
        return roleRepository.updateById(role);
    }

    @Override
    public int updateStatus(List<Long> ids, boolean status) {
        LambdaUpdateWrapper<SysRole> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(SysRole::getId, ids);
        wrapper.set(SysRole::getStatus, status);
        return roleRepository.update(null, wrapper);
    }

    @Override
    public List<SysRole> getRoleList() {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getStatus, true);
        return roleRepository.selectList(wrapper);
    }

    @Override
    public PageList<SysRole> getRoleList(int start, int size, SysRole role) {
        return PageList.of(roleRepository, role, start, size);
    }

    @Override
    public List<SysRole> findRolesByIds(String roleids) {
        return roleRepository.selectBatchIds(StringUtils.stringToList(roleids));
    }

}
