package com.cjwx.spark.quartz.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjwx.spark.quartz.entity.QtzExecuteLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends BaseMapper<QtzExecuteLog> {

}
