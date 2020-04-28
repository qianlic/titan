package com.cjwx.titan.activiti.config;

import com.cjwx.titan.engine.config.ApplicationConfiguration;
import com.cjwx.titan.engine.config.WebMvcConfiguration;
import org.activiti.engine.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  14:10
 */
@Configuration
@ComponentScan("com.cjwx.titan.activiti")
@Import({ApplicationConfiguration.class, WebMvcConfiguration.class})
public class ActivitiConfiguration {

    @Bean
    public ProcessEngine processEngine() {
        return ProcessEngines.getDefaultProcessEngine();
    }

    //运行时服务
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    //库服务
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    //任务服务
    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    //管理服务
    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    //身份服务
    @Bean
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    //历史服务
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    //表单服务
    @Bean
    public FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

}
