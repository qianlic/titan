package com.cjwx.titan.quartz.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzScheduleJobBean;

import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月03日 9:10
 */
public interface JobDao {

    PageList<QtzScheduleJobBean> select(int start, int size, Map<String, Object> where);

}
