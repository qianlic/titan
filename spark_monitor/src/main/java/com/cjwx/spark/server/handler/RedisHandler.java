package com.cjwx.spark.server.handler;

import com.cjwx.spark.server.helper.RedisHelper;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import io.swagger.annotations.Api;

import java.util.Map;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@Api(tags = "系统监控-REDIS")
@RestHandler("/redis/")
public class RedisHandler {

    @RestMethod("info")
    public Map<String, Object> info() {
        return RedisHelper.getRedisInfo();
    }

}
