package com.cjwx.titan.server.filter;

import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.engine.web.http.Result;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Description:
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
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public InputStream getBody() {
                String message = cause.getMessage();
                if (StringUtils.isEmpty(message) && cause.getCause() != null) {
                    message = cause.getCause().getMessage();
                }
                Result result = new Result(false, message);
                return new ByteArrayInputStream(result.toString().getBytes());
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
