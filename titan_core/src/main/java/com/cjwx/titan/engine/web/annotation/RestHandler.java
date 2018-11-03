package com.cjwx.titan.engine.web.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @Description: 对该注解类返回值进行REST封装
 * @Author: qian li
 * @Date: 2018年08月19日 12:52
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface RestHandler {
    String value() default "";
}
