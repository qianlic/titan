package com.cjwx.titan.engine.web.http;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: Request请求工具类
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
@Slf4j
public class RequestHelper {

    public static HttpServletRequest getRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) attributes).getRequest();
    }

    public static String getClientIp() {
        try {
            HttpServletRequest request = getRequest();
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        } catch (Exception e) {
            return "UnknownHost";
        }
    }

    public static JSONObject requestJson() throws IOException {
        String jsonString = requestString();
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return JSONObject.parseObject(jsonString);
    }

    public static String requestString() {
        try {
            InputStreamReader ir = new InputStreamReader(getRequest().getInputStream(), HttpConstant.DEFAULT_CHARSET);
            BufferedReader br = new BufferedReader(ir);
            StringBuffer sb = new StringBuffer();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            log.error("请求信息异常", e);
        }
        return null;
    }

}
