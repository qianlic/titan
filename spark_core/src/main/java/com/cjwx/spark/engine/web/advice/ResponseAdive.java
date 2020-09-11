package com.cjwx.spark.engine.web.advice;

import com.alibaba.fastjson.JSON;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.http.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description: 返回值处理
 * @Author: qian li
 * @Date: 2018年08月18日 16:41
 */
@ControllerAdvice
public class ResponseAdive implements ResponseBodyAdvice {

    /**
     * 支持Handler接口实现类
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getMethod().getDeclaringClass().isAnnotationPresent(RestHandler.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        boolean flag = o instanceof String;
        if (!(o instanceof Result)) {
            o = new Result(o);
        }
        if (flag) {
            o = JSON.toJSONString(o);
        }
        return o;
    }

}
