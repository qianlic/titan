package com.cjwx.titan.quartz.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.entity.QtzScheduleEntity;
import org.quartz.SchedulerException;

import java.util.Map;

public interface ScheduleService {

    PageList<QtzScheduleEntity> getScheduleList(int start, int size, Map<String, Object> wheres);

    void create(QtzScheduleEntity job);

    void update(QtzScheduleEntity job);

    void start() throws SchedulerException;

    void shutdown() throws SchedulerException;

}