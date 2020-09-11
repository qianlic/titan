package com.cjwx.spark.engine.repository;

import com.cjwx.spark.engine.entity.SysClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description: 客户端
 * @Author: qian li
 * @Date: 2020年09月09日 15:32
 */
@Repository
public interface ClientRepository extends JpaRepository<SysClientEntity, Long> {

    SysClientEntity findByClientCode(String clientCode);

}
