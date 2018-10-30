package com.cjwx.titan.boot;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        HttpConstant.VERSION = "0.0.1";
        HttpConstant.EXCLUSIONS = new ArrayList<>();
        HttpConstant.EXCLUSIONS.add("/system/user/token");
        SpringApplication.run(BootApplication.class, args);
    }

}
