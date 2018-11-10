package com.cjwx.titan.quartz.execute;

import com.alibaba.fastjson.JSON;
import com.cjwx.titan.engine.util.DateUtils;
import com.cjwx.titan.engine.web.http.Result;
import com.cjwx.titan.quartz.bean.QtzExecuteLogBean;
import com.cjwx.titan.quartz.config.QuartzConfiguration;
import com.cjwx.titan.quartz.service.ExecuteLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: Job执行服务监听
 * @Author: qian li
 * @Date: 2018年11月09日 15:23
 */
@Slf4j
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
        QtzExecuteLogBean log = new QtzExecuteLogBean();
        log.setExecutetime(DateUtils.now());

        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        log.setTaskname(jobDetail.getKey().getName());
        log.setTaskgroup(jobDetail.getKey().getGroup());

        JobDataMap data = jobDetail.getJobDataMap();
        log.setServer(data.getString(QuartzConfiguration.SERVER_KEY));
        log.setPath(data.getString(QuartzConfiguration.PATH_KEY));
        log.setParam(data.getString(QuartzConfiguration.DATA_KEY));

        Object result = jobExecutionContext.getResult();
        if (result != null && result instanceof Result) {
            Result r = (Result) result;
            log.setSuccess(r.isSuccess());
            log.setMessage(r.getMessage());
            log.setResult(JSON.toJSONString(r.getData()));
        }
        executeLogService.create(log);
    }

}
