package com.cjwx.titan.server;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.util.Arrays;

@EnableZuulProxy
@EnableEurekaServer
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        HttpConstant.VERSION = "0.0.1";
        HttpConstant.EXCLUSIONS = Arrays.asList("/system/user/token");
        SpringApplication.run(ServerApplication.class, args);
    }

}
