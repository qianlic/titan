package com.cjwx.titan.quartz.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzScheduleJobBean;

import java.util.Map;

public interface ScheduleService {

    PageList<QtzScheduleJobBean> getScheduleList(int start, int size, Map<String, Object> wheres);

    void create(QtzScheduleJobBean job);

    void update(QtzScheduleJobBean job);

    void delete(QtzScheduleJobBean job);

    void pause(QtzScheduleJobBean job);

    void resume(QtzScheduleJobBean job);

    void start(QtzScheduleJobBean job);

}