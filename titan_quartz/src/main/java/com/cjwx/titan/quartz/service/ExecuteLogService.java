package com.cjwx.titan.quartz.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzLogBean;
import org.quartz.JobExecutionContext;

import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 15:44
 */
public interface ExecuteLogService {

    void create(JobExecutionContext job);

    PageList<QtzLogBean> getLogList(int start, int size, Map<String, Object> wheres);

}
