package com.cjwx.titan.engine.web.handler;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.engine.web.http.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Description: 默认返回信息
 * @Author: qian li
 * @Date: 2018年08月18日 11:57
 */
@RestController
public class DefaultHandler {

    @Value("${spring.application.name}")
    private String serviceName;
    @Autowired
    private List<RequestMappingHandlerMapping> handlerMappings;

    @RestMethod(value = HttpConstant.DEFAULT_FIX, method = {RequestMethod.POST, RequestMethod.GET})
    public Result error() {
        return new Result(false, "远程服务未被发现！");
    }

    @RestMethod("/urlStream")
    public Stream<String> urlStream() {
        return handlerMappings.stream()
                .map(RequestMappingHandlerMapping::getHandlerMethods)
                .flatMap(h -> h.entrySet().stream()
                        .filter(e -> e.getValue().getBeanType().isAnnotationPresent(RestHandler.class))
                        .map(Map.Entry::getKey))
                .flatMap(h -> h.getPatternsCondition().getPatterns().stream())
                .map(url -> "/" + serviceName + url.replace("//", "/").toLowerCase())
                .distinct();
    }

}
