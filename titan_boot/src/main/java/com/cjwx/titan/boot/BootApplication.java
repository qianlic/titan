package com.cjwx.titan.boot;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        HttpConstant.VERSION = "0.0.1";
        HttpConstant.EXCLUSIONS = Arrays.asList("/system/user/token");
        SpringApplication.run(BootApplication.class, args);
    }

}
