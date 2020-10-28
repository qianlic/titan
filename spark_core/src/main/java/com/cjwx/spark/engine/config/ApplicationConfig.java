package com.cjwx.spark.engine.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 核心功能引入
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@MapperScan("com.cjwx.spark.**.repository")
public class ApplicationConfig {

    /**
     * Rest服务客户端
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * mybatis plus翻页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
