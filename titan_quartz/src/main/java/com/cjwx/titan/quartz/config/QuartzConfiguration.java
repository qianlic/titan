package com.cjwx.titan.quartz.config;

import com.cjwx.titan.engine.config.ApplicationConfiguration;
import com.cjwx.titan.engine.config.WebMvcConfiguration;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description: 核心功能引入
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@Data
@Configuration
@ImportResource("classpath:spring-quartz.xml")
@Import({ApplicationConfiguration.class, WebMvcConfiguration.class})
public class QuartzConfiguration {

}
