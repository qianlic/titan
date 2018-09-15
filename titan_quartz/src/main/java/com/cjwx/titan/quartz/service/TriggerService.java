package com.cjwx.titan.quartz.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.entity.QtzTriggerEntity;
import org.quartz.ScheduleBuilder;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.util.Map;

public interface TriggerService {

    PageList<QtzTriggerEntity> getTriggerList(int start, int size, Map<String, Object> wheres);

    void createTrigger(QtzTriggerEntity trigger);

    void updateTrigger(QtzTriggerEntity trigger);

    QtzTriggerEntity getTriggerEntity(QtzTriggerEntity trigger);

    Trigger getTrigger(QtzTriggerEntity trigger) throws SchedulerException;

    String getRunState(QtzTriggerEntity trigger) throws SchedulerException;

    boolean delete(QtzTriggerEntity trigger);

    void pause(QtzTriggerEntity qjb);

    void resume(QtzTriggerEntity qjb);

}