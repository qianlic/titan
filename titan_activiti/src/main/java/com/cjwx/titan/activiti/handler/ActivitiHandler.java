package com.cjwx.titan.activiti.handler;

import com.cjwx.titan.activiti.service.ActivitiService;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import io.swagger.annotations.Api;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  15:46
 */
@Api(tags = "工作流-流程管理")
@RestHandler("/activity/")
public class ActivitiHandler {

    @Resource
    private ActivitiService activityService;

    @RestMethod("list")
    public String list() {
        return "";
    }


}
