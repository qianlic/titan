package com.cjwx.titan.quartz.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzJobBean;

import java.util.Map;

public interface ScheduleService {

    PageList<QtzJobBean> getScheduleList(int start, int size, Map<String, Object> wheres);

    void create(QtzJobBean job);

    void update(QtzJobBean job);

    void delete(QtzJobBean job);

    void pause(QtzJobBean job);

    void resume(QtzJobBean job);

    void start(QtzJobBean job);

}