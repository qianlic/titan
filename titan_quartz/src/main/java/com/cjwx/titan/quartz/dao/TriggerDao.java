package com.cjwx.titan.quartz.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.entity.QtzScheduleEntity;

import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月03日 9:10
 */
public interface TriggerDao {

    PageList<QtzScheduleEntity> select(int start, int size, Map<String, Object> where);

}
