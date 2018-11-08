package com.cjwx.titan.quartz.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.entity.QtzScheduleEntity;

import java.util.Map;

public interface ScheduleService {

    PageList<QtzScheduleEntity> getScheduleList(int start, int size, Map<String, Object> wheres);

    void create(QtzScheduleEntity job);

    void update(QtzScheduleEntity job);

    void delete(QtzScheduleEntity job);

    void pause(QtzScheduleEntity job);

    void resume(QtzScheduleEntity job);

    void start(QtzScheduleEntity job);

}