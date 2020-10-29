package com.cjwx.spark.engine.web.handler;

import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
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
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> ResultDTO<T> exceptionGet(Exception e) {
        return ResultUtils.fail("操作失败!" + e.getMessage());
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    @ResponseBody
    public <T> ResultDTO<T> exceptionGet(BadSqlGrammarException e) {
        return ResultUtils.fail("SQL错误!" + e.getMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public <T> ResultDTO<T> exceptionGet(HttpMessageNotReadableException e) {
        return ResultUtils.fail("JSON解析异常!" + e.getMessage());
    }

}
