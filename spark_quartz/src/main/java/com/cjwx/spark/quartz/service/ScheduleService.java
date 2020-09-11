package com.cjwx.spark.quartz.service;

import com.cjwx.spark.quartz.entity.QtzJobBean;
import com.cjwx.spark.engine.core.model.PageList;

public interface ScheduleService {

    PageList<QtzJobBean> getScheduleList(int start, int size, QtzJobBean wheres);

    void create(QtzJobBean job);

    void update(QtzJobBean job);

    void delete(QtzJobBean job);

    void pause(QtzJobBean job);

    void resume(QtzJobBean job);

    void start(QtzJobBean job);

}