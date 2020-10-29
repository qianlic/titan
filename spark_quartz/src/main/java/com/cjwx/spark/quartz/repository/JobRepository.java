package com.cjwx.spark.quartz.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjwx.spark.quartz.entity.QtzJobKey;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends BaseMapper<QtzJobKey> {

}
