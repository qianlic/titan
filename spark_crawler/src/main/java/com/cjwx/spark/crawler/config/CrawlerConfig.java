package com.cjwx.spark.crawler.config;

import com.cjwx.spark.crawler.crawler.schedule.CrawlerScheduler;
import com.cjwx.spark.engine.config.ApplicationConfig;
import com.cjwx.spark.engine.config.WebMvcConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description: 核心功能引入
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@Configuration
@Import({ApplicationConfig.class, WebMvcConfig.class})
@ComponentScan("com.cjwx.spark.crawler")
public class CrawlerConfig {

    @Bean
    public CrawlerScheduler scheduler(CrawlerScheduler crawlerScheduler) {
        crawlerScheduler.start(10, 10, 3);
        return crawlerScheduler;
    }

}
