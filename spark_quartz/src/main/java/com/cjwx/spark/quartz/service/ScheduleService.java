package com.cjwx.spark.quartz.service;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.quartz.dto.QtzJobDTO;

public interface ScheduleService {

    ResultDTO<PageDTO<QtzJobDTO>> getScheduleList(QtzJobDTO job, int start, int size) throws Exception;

    void create(QtzJobDTO job);

    void update(QtzJobDTO job);

    void delete(QtzJobDTO job);

    void pause(QtzJobDTO job);

    void resume(QtzJobDTO job);

    void start(QtzJobDTO job);

}