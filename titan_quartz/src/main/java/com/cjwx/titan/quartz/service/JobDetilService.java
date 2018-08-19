package com.cjwx.titan.quartz.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.entity.QtzJobEntity;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

public interface JobDetilService {

    List<JobKey> getJobKeyList();

    PageList<QtzJobEntity> getJobDetailList(int start, int size, Map<String, Object> wheres);

    void createJobDetail(QtzJobEntity entity);

    void updateJobDetail(QtzJobEntity entity);

    QtzJobEntity getJobEntity(QtzJobEntity entity);

    List<QtzJobEntity> selectRun() throws SchedulerException;

    JobDetail getJobDetail(QtzJobEntity job) throws SchedulerException;

    boolean delete(QtzJobEntity qjb);

    void execute(QtzJobEntity qjb);

    void pause(QtzJobEntity qjb);

    void resume(QtzJobEntity qjb);

}