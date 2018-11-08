package com.cjwx.titan.monitor.handler;

import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.monitor.bean.MemoryBean;
import com.cjwx.titan.monitor.helper.MemoryHelper;
import io.swagger.annotations.Api;

/**
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@Api(tags = "系统监控-内存管理")
@RestHandler("/memory/")
public class MemoryHandler {

    @RestMethod("info")
    public MemoryBean info() {
        return MemoryHelper.getMemoryInformations();
    }

}
