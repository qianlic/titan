package com.cjwx.spark.server.zuul;

import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.engine.web.helper.RibbonClientHelper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Description: 路由请求失败熔断
 * @Author: qian li
 * @Date: 2018年11月07日 13:46
 */
@Component
public class ProducerFallback implements FallbackProvider {

    private static HttpStatus ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    @Override
    public String getRoute() {
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpHeaders getHeaders() {
                return RibbonClientHelper.getDefaultHeaders();
            }

            @Override
            public InputStream getBody() {
                String message = cause.getMessage();
                if (StringUtils.isEmpty(message) && cause.getCause() != null) {
                    message = cause.getCause().getMessage();
                }
                return new ByteArrayInputStream(ResultUtils.fail(message).toString().getBytes());
            }

            @Override
            public HttpStatus getStatusCode() {
                return ERROR_STATUS;
            }

            @Override
            public String getStatusText() {
                return ERROR_STATUS.getReasonPhrase();
            }

            @Override
            public int getRawStatusCode() {
                return ERROR_STATUS.value();
            }

            @Override
            public void close() {

            }
        };
    }
}
