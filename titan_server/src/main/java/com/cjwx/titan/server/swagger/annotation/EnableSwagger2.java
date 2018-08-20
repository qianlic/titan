package com.cjwx.titan.server.swagger.annotation;

import com.cjwx.titan.server.swagger.configuration.Rest2DocumentationConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月20日 10:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({Rest2DocumentationConfiguration.class})
public @interface EnableSwagger2 {
}
