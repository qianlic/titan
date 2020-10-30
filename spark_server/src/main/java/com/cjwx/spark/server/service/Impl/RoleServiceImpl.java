package com.cjwx.spark.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.entity.SysRole;
import com.cjwx.spark.engine.repository.RoleRepository;
import com.cjwx.spark.engine.util.MapperUtils;
import com.cjwx.spark.engine.util.ObjectUtils;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.server.dto.SysRoleDTO;
import com.cjwx.spark.server.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 角色服务
 * @Author: qian li
 * @Date: 2020/10/29 8:30
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public ResultDTO<Integer> createRole(SysRoleDTO role) throws Exception {
        return MapperUtils.insert(roleRepository, ObjectUtils.convert(role, SysRole.class));
    }

    @Override
    public ResultDTO<Integer> updateRole(SysRoleDTO role) throws Exception {
        return MapperUtils.update(roleRepository, ObjectUtils.convert(role, SysRole.class));
    }

    @Override
    public ResultDTO<Integer> deleteRole(List<Long> ids) {
        return MapperUtils.delete(roleRepository, ids);
    }

    @Override
    public ResultDTO<List<SysRoleDTO>> getRoleList() throws Exception {
        return MapperUtils.list(roleRepository, new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getStatus, true), SysRoleDTO.class);
    }

    @Override
    public ResultDTO<PageDTO<SysRoleDTO>> getRoleList(SysRoleDTO role, int start, int size) throws Exception {
        return MapperUtils.pageList(roleRepository, ObjectUtils.convert(role, SysRole.class), start, size, SysRoleDTO.class);
    }

    @Override
    public ResultDTO<Integer> updateStatus(List<Long> ids, boolean status) {
        return MapperUtils.update(roleRepository, new LambdaUpdateWrapper<SysRole>()
                .in(SysRole::getId, ids)
                .set(SysRole::getStatus, status));
    }

    @Override
    public ResultDTO<List<SysRoleDTO>> findByUserId(Long userId) throws Exception {
        return ResultUtils.success(ObjectUtils.convert(roleRepository.findByUserId(userId), SysRoleDTO.class));
    }

}
