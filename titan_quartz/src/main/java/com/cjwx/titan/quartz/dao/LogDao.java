package com.cjwx.titan.quartz.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzScheduleLogBean;

import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 14:47
 */
public interface LogDao {

    long insert(Object bean);

    PageList<QtzScheduleLogBean> select(int start, int size, Map<String, Object> where);

}
