package com.cjwx.titan.quartz.execute;

import com.cjwx.titan.quartz.service.ExecuteLogService;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 15:23
 */
@Component
public class JobExecuteListener implements JobListener {

    @Resource
    private ExecuteLogService executeLogService;

    @Override
    public String getName() {
        return "JobExecuteListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        executeLogService.create(jobExecutionContext);
    }

}
