package com.cjwx.spark.engine.web.handler;

import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
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
    @Resource
    private List<RequestMappingHandlerMapping> handlerMappings;

    //@RestMethod(value = HttpConstant.DEFAULT_FIX, method = {RequestMethod.POST, RequestMethod.GET})
    public ResultDTO<String> error() {
        return ResultUtils.fail("远程服务未被发现！");
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
