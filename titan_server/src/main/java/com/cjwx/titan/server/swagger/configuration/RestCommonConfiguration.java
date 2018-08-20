package com.cjwx.titan.server.swagger.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import springfox.documentation.swagger.web.SwaggerApiListingReader;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月20日 10:15
 */
@Configuration
@ComponentScan(basePackages = {
        "springfox.documentation.swagger.schema",
        "springfox.documentation.swagger.readers",
        "springfox.documentation.swagger.web",
}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SwaggerApiListingReader.class)
})
public class RestCommonConfiguration {
    public RestCommonConfiguration() {
    }
}
