package com.cjwx.spark.engine.web.http;

import com.alibaba.fastjson.JSON;
import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: Response响应工具类
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
@Slf4j
public class ResponseHelper {

    public static HttpServletResponse getResponse() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) attributes).getResponse();
    }

    public static <T> void writeJson(ResultDTO<T> date) {
        writeJson(HttpServletResponse.SC_OK, date);
    }

    public static <T> void writeJson(int status, ResultDTO<T> date) {
        writeJson(getResponse(), status, date);
    }


    public static <T> void writeJson(HttpServletResponse response, ResultDTO<T> date) {
        writeJson(response, HttpServletResponse.SC_OK, date);
    }


    public static <T> void writeJson(HttpServletResponse response, int status, ResultDTO<T> date) {
        try {
            response.setContentType(AppConstant.DEFAULT_MEDIA_TYPE);
            response.setStatus(status);
            response.getWriter().write(JSON.toJSONString(date));
        } catch (IOException e) {
            log.error("返回信息异常", e);
        }
    }

}
