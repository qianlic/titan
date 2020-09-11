package com.cjwx.spark.monitor.config;

import com.cjwx.spark.engine.config.ApplicationConfig;
import com.cjwx.spark.engine.config.WebMvcConfig;
import com.cjwx.spark.oauth2.annotaion.EnableAuthorizationCenter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 核心功能引入
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@Configuration
@EnableAuthorizationCenter
@Import({ApplicationConfig.class, WebMvcConfig.class})
public class MonitorConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
