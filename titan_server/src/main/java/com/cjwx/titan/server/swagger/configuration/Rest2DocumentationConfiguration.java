package com.cjwx.titan.server.swagger.configuration;

import org.springframework.context.annotation.*;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.scanners.MediaTypeReader;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月20日 10:11
 */
@Configuration
@Import({SpringfoxWebMvcConfiguration.class, RestCommonConfiguration.class})
@ComponentScan(basePackages = {
        "springfox.documentation.swagger2.web",
        "springfox.documentation.swagger2.mappers"
}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MediaTypeReader.class)
})
public class Rest2DocumentationConfiguration {

    public Rest2DocumentationConfiguration() {
    }

    @Bean
    public JacksonModuleRegistrar swagger2Module() {
        return new Swagger2JacksonModule();
    }

}
