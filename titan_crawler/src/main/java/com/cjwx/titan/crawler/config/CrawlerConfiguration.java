package com.cjwx.titan.crawler.config;

import com.cjwx.titan.engine.config.ApplicationConfiguration;
import com.cjwx.titan.engine.config.WebMvcConfiguration;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description: 核心功能引入
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@Data
@Configuration
@Import({ApplicationConfiguration.class, WebMvcConfiguration.class})
@ComponentScan("com.cjwx.titan.engine.core")
public class CrawlerConfiguration {

}
