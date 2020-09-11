package com.cjwx.spark.server.service.Impl;

import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysRoleEntity;
import com.cjwx.spark.engine.repository.RoleRepository;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.server.service.RoleService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public SysRoleEntity createRole(SysRoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public int deleteRole(List<Long> ids) {
        return roleRepository.deleteByIdIn(ids);
    }

    @Override
    public SysRoleEntity updateRole(SysRoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public int updateStatus(List<Long> ids, boolean status) {
        return roleRepository.updateStatusByIdIn(status, ids);
    }

    @Override
    public List<SysRoleEntity> getRoleList() {
        return roleRepository.findByStatus(true);
    }

    @Override
    public PageList<SysRoleEntity> getRoleList(int start, int size, SysRoleEntity role) {
        return PageList.of(roleRepository.findAll(Example.of(role), PageRequest.of(start, size)));
    }

    @Override
    public List<SysRoleEntity> findRolesByIds(String roleids) {
        return roleRepository.findAllById(StringUtils.stringToList(roleids)
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList()));
    }

}
