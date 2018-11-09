package com.cjwx.titan.quartz.execute;

import com.cjwx.titan.engine.web.helper.RibbonClientHelper;
import com.cjwx.titan.engine.web.http.Result;
import com.cjwx.titan.quartz.config.QuartzConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

/**
 * @Description: Job执行服务接口
 * @Author: qian li
 * @Date: 2018年03月29日 19:09
 */
@Slf4j
public class JobExecuteAdvice implements Job {

    /**
     * 执行实际任务
     */
    @Override
    public void execute(JobExecutionContext context) {
        try {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String service = dataMap.getString(QuartzConfiguration.SERVER_KEY);
            String path = dataMap.getString(QuartzConfiguration.PATH_KEY);
            String data = dataMap.getString(QuartzConfiguration.DATA_KEY);
            Result result = RibbonClientHelper.doPost(service, path, data, Result.class);
            context.setResult(result);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            context.setResult(new Result(false, e.getMessage()));
        }
    }

}