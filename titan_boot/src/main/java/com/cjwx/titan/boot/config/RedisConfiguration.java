package com.cjwx.titan.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description: REDIS配置
 * @Author: qian li
 * @Date: 2018年10月30日 13:14
 */
@Configuration
@PropertySource("classpath:config/redis.properties")
@ImportResource("classpath:spring-reids.xml")
public class RedisConfiguration {

}
