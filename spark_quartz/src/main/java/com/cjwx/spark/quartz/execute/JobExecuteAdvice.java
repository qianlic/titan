package com.cjwx.spark.quartz.execute;

import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.web.helper.RibbonClientHelper;
import com.cjwx.spark.quartz.config.QuartzConfig;
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
            String service = dataMap.getString(QuartzConfig.SERVER_KEY);
            String path = dataMap.getString(QuartzConfig.PATH_KEY);
            String data = dataMap.getString(QuartzConfig.DATA_KEY);
            context.setResult(RibbonClientHelper.doPost(service, path, data, ResultDTO.class));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            context.setResult(ResultUtils.fail(e.getMessage()));
        }
    }

}