package com.cjwx.titan.engine.config;

import org.springframework.context.annotation.*;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: WebMvc配置
 * @Author: qian li
 * @Date: 2018年10月30日 19:12
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.cjwx.titan.engine.web")
@Import(SwaggerConfiguration.class)
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(100000000);
        resolver.setMaxInMemorySize(10240000);
        return resolver;
    }

}
