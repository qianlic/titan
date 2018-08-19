package com.cjwx.titan.engine.core.web.http;

import lombok.Data;

/**
 * @Description: Response响应数据
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
@Data
public class Result<T> {

    private int status;

    private String message;

    private T data;

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(ResultStatus resultStatus) {
        this(resultStatus.getStatus(), resultStatus.getMessage());
    }

    public Result(ResultStatus resultStatus, T data) {
        this(resultStatus);
        this.data = data;
    }

}
