package com.cjwx.titan.monitor.handler;

import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.monitor.helper.RedisHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @Description: 系统监控-REDIS
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@RestHandler
@RequestMapping(value = "/system/redis/", method = RequestMethod.POST)
public class RedisHandler {

    @RequestMapping("info")
    public Map<String, Object> info() {
        return RedisHelper.getRedisInfo();
    }

}
