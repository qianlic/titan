package com.cjwx.titan.quartz.execute;

import java.lang.reflect.Method;

import com.cjwx.titan.engine.core.helper.ApplicationContextHelper;
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
            String serviceName = dataMap.getString("SERVICE");
            String methodName = dataMap.getString("METHOD");
            String data = dataMap.getString("DATA");

            BaseExecService service = (BaseExecService) ApplicationContextHelper.getBean(serviceName);
            Method method = service.getClass().getDeclaredMethod(methodName, String.class);
            method.invoke(service, data);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

}