package com.cjwx.spark.quartz.config;

import com.cjwx.spark.engine.config.ApplicationConfig;
import com.cjwx.spark.engine.config.WebMvcConfig;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Description: 核心功能引入
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@Configuration
@ComponentScan("com.cjwx.spark.quartz")
@Import({ApplicationConfig.class, WebMvcConfig.class})
public class QuartzConfig implements SchedulerFactoryBeanCustomizer {

    public static final String SERVER_KEY = "SERVER";
    public static final String PATH_KEY = "PATH";
    public static final String DATA_KEY = "DATA";

    @Autowired
    private JobListener jobListener;

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        schedulerFactoryBean.setBeanName("scheduler");
        schedulerFactoryBean.setStartupDelay(10);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setGlobalJobListeners(jobListener);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
    }
}
