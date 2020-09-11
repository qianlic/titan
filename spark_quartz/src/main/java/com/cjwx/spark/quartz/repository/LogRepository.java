package com.cjwx.spark.quartz.repository;

import com.cjwx.spark.quartz.entity.QtzExecuteLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<QtzExecuteLogEntity, Long> {

    int deleteByIdIn(List<Long> ids);

}
