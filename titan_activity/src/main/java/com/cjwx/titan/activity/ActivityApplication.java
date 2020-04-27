package com.cjwx.titan.activity;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class, args);
    }

}
