package com.cjwx.titan.activity.config;

import com.cjwx.titan.engine.config.ApplicationConfiguration;
import com.cjwx.titan.engine.config.WebMvcConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  14:10
 */
@Configuration
@ComponentScan("com.cjwx.titan.activity")
@Import({ApplicationConfiguration.class, WebMvcConfiguration.class})
public class ActivityConfiguration {


}
