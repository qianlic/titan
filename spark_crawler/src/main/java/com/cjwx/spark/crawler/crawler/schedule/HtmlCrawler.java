package com.cjwx.spark.crawler.crawler.schedule;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Description: 对该注解类返回值进行REST封装
 * @Author: qian li
 * @Date: 2018年08月19日 12:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface HtmlCrawler {

    @AliasFor(annotation = Component.class)
    String value() default "";

    String url() default "";

    String tag() default "";

}
