package com.cjwx.spark.monitor.handler;

import com.cjwx.spark.monitor.entity.MemoryBean;
import com.cjwx.spark.monitor.helper.MemoryHelper;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
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
