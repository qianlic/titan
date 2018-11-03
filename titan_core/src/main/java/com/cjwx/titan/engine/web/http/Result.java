package com.cjwx.titan.engine.web.http;

import lombok.Data;

/**
 * @Description: Response响应数据
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
@Data
public class Result<T> {

    private boolean success = true;
    private String message = "操作成功！";
    private T data;

    public Result(T data) {
        this.data = data;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
