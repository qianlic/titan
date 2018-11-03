package com.cjwx.titan.engine.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Description: 核心功能引入
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@Data
@Configuration
@Import({FileToolConfiguration.class, DataSourceConfiguration.class})
@EntityScan("com.cjwx.titan.**.bean")
@EnableJpaRepositories
public class ApplicationConfiguration {
}