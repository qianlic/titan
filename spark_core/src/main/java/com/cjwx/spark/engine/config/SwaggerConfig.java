package com.cjwx.spark.engine.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Swagger文档配置
 * @Author: qian li
 * @Date: 2020/10/28 12:47
 */
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("API")
                .version("0.0.1")
                .build();
    }

    private List<Parameter> parameters() {
        return new ArrayList<Parameter>() {
            {
                add(new ParameterBuilder().name("Authorization")
                        .description("令牌")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false).build());
            }
        };
    }
}
