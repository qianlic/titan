package com.cjwx.spark.engine.repository;

import com.cjwx.spark.engine.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 用户
 * @Author: qian li
 * @Date: 2020年09月09日 15:32
 */
@Repository
public interface UserRepository extends JpaRepository<SysUserEntity, Long> {

    SysUserEntity findByUserCode(String userCode);

    int deleteByIdIn(List<Long> ids);

    @Modifying
    @Query("update SysUserEntity u set u.status = :status where u.id in (:ids)")
    int updateStatusByIdIn(@Param("status") boolean status,
                           @Param("ids") List<Long> ids);

    @Modifying
    @Query("update SysUserEntity u set u.password = :password,u.salt = :salt where u.id in (:ids)")
    int updatePasswordAndSaltByIdIn(@Param("password") String password,
                                    @Param("salt") String salt,
                                    @Param("ids") List<Long> ids);

}
