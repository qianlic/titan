package com.cjwx.titan.server.config;

import com.cjwx.titan.engine.config.ApplicationConfiguration;
import com.cjwx.titan.engine.config.WebMvcConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月03日 10:20
 */
@Configuration
@Import({ApplicationConfiguration.class, WebMvcConfiguration.class})
@ComponentScan("com.cjwx.titan.server.zuul")
public class ServerConfiguration {

}
