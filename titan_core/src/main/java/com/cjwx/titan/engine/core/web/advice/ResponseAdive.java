package com.cjwx.titan.engine.core.web.advice;

import com.cjwx.titan.engine.core.web.http.Result;
import com.cjwx.titan.engine.core.web.http.ResultStatus;
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

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Result) {
            return o;
        }
        return new Result(ResultStatus.STATUS_0, o);
    }

}
