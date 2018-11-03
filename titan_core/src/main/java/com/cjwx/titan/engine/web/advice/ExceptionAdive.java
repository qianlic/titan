package com.cjwx.titan.engine.web.advice;

import com.cjwx.titan.engine.core.exception.ServiceException;
import com.cjwx.titan.engine.web.http.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 异常处理
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdive {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e) {
        String message;
        if (e instanceof ServiceException) {
            message = e.getMessage();
        } else {
            message = "系统错误: " + e.getMessage();
            log.error("请求发生异常", e);
        }
        return new Result(false, message);
    }

}
