package com.cjwx.titan.activiti.service.impl;

import com.cjwx.titan.activiti.service.ActivitiService;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  16:47
 */
@Service("activityService")
public class ActivitiServiceImpl implements ActivitiService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    @Resource
    private ManagementService managementService;

    @Resource
    private IdentityService identityService;

    @Resource
    private HistoryService historyService;

    @Resource
    private FormService formService;

    @Override
    public void deploy(String name) {
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(name).deploy();
        System.out.println(deployment.getId() + "-" + deployment.getName());
    }

}
