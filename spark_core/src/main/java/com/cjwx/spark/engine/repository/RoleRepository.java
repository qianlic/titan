package com.cjwx.spark.engine.repository;

import com.cjwx.spark.engine.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 角色
 * @Author: qian li
 * @Date: 2020年09月09日 15:32
 */
@Repository
public interface RoleRepository extends JpaRepository<SysRoleEntity, Long> {

    List<SysRoleEntity> findByStatus(boolean status);

    int deleteByIdIn(List<Long> ids);

    @Modifying
    @Query("update SysRoleEntity r set r.status = :status where r.id in (:ids)")
    int updateStatusByIdIn(@Param("status") boolean status,
                           @Param("ids") List<Long> ids);

}
