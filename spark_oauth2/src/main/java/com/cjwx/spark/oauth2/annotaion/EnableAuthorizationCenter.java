package com.cjwx.spark.oauth2.annotaion;

import com.cjwx.spark.oauth2.config.AuthorizationServerConfig;
import com.cjwx.spark.oauth2.config.WebSecurityConfig;
import com.cjwx.spark.oauth2.service.ClientDetailsServiceImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月10日 9:19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AuthorizationServerConfig.class, WebSecurityConfig.class, ClientDetailsServiceImpl.class})
public @interface EnableAuthorizationCenter {
}
