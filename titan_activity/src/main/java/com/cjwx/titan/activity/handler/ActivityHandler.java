package com.cjwx.titan.activity.handler;

import com.cjwx.titan.activity.service.ActivityService;
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
public class ActivityHandler {

    @Resource
    private ActivityService activityService;

    @RestMethod("list")
    public String list() {
        return "";
    }


}
