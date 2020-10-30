package com.cjwx.spark.engine.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjwx.spark.engine.entity.SysRole;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 角色
 * @Author: qian li
 * @Date: 2020年09月09日 15:32
 */
@Repository
public interface RoleRepository extends BaseMapper<SysRole> {

    @Select("SELECT t1.* " +
            "FROM sys_role t1,sys_user_role t2 " +
            "WHERE t1.id = t2.role_id " +
            "AND t2.user_id = #{userId}")
    List<SysRole> findByUserId(Long userId);

}
