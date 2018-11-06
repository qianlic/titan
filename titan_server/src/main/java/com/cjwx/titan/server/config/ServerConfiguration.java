package com.cjwx.titan.server.config;

import com.cjwx.titan.engine.config.ApplicationConfiguration;
import com.cjwx.titan.engine.config.WebMvcConfiguration;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月03日 10:20
 */
@Data
@Configuration
@Import({ApplicationConfiguration.class, WebMvcConfiguration.class})
@ImportResource("classpath:spring-server.xml")
public class ServerConfiguration {

}
