package com.cjwx.titan.activity.service.impl;

import com.cjwx.titan.activity.service.ActivityService;
import org.activiti.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  16:47
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

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

}
