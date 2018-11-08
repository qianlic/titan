package com.cjwx.titan.quartz.execute;

import com.cjwx.titan.engine.web.helper.RibbonClientHelper;
import com.cjwx.titan.engine.web.http.Result;
import com.cjwx.titan.quartz.config.QuartzConfiguration;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Description: 简单的实现了Spring QuartzJobBean接口
 * @Author: qian li
 * @Date: 2018年03月29日 19:09
 */
public class BaseExecService extends QuartzJobBean {

    /**
     * 执行实际任务
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String service = dataMap.getString(QuartzConfiguration.SERVER_KEY);
            String path = dataMap.getString(QuartzConfiguration.PATH_KEY);
            String data = dataMap.getString(QuartzConfiguration.DATA_KEY);
            Result result = RibbonClientHelper.doPost(service, path, data, Result.class);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

}