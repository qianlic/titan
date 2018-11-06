package com.cjwx.titan.server.swagger;

import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月06日 9:29
 */
@Component
@Primary
public class DocumentationProvider implements SwaggerResourcesProvider {

    public static final String VERSION = "2.0";
    private final RouteLocator routeLocator;

    public DocumentationProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        return routeLocator.getRoutes().stream()
                .map(r -> swaggerResource(r.getId(), r.getFullPath().replace("**", "v2/api-docs")))
                .collect(Collectors.toList());

    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(VERSION);
        return swaggerResource;
    }

}
