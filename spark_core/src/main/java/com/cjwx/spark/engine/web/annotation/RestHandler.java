package com.cjwx.spark.engine.web.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @Description: 对该注解类返回值进行REST封装
 * @Author: qian li
 * @Date: 2018年08月19日 12:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface RestHandler {

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

}
