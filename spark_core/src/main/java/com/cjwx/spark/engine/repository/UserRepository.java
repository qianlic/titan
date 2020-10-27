package com.cjwx.spark.engine.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjwx.spark.engine.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户
 * @Author: qian li
 * @Date: 2020年09月09日 15:32
 */
@Repository
public interface UserRepository extends BaseMapper<SysUser> {

}
