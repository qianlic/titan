package com.cjwx.titan.server.swagger;

import com.cjwx.titan.engine.web.annotation.RestHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingBuilderPlugin;
import springfox.documentation.spi.service.contexts.ApiListingContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月19日 17:16
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1000)
public class RestApiListingReader implements ApiListingBuilderPlugin {

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    @Override
    public void apply(ApiListingContext apiListingContext) {
        Class<?> controllerClass = apiListingContext.getResourceGroup().getControllerClass();
        RestHandler apiAnnotation = controllerClass.getAnnotation(RestHandler.class);
        Set<String> tagNames = new HashSet<>();
        tagNames.add(apiListingContext.getResourceGroup().getGroupName());
        apiListingContext.apiListingBuilder().description(apiAnnotation.value()).tagNames(tagNames);
    }

}

