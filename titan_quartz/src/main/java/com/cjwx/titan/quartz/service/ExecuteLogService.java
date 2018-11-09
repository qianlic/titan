package com.cjwx.titan.quartz.service;

import org.quartz.JobExecutionContext;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 15:44
 */
public interface ExecuteLogService {

    void create(JobExecutionContext job);

}
