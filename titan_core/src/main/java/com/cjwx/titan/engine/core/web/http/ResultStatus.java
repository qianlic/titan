package com.cjwx.titan.engine.core.web.http;

/**
 * @Description: Response响应状态
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
public enum ResultStatus {

    STATUS_0(0, "操作成功!"), STATUS_1(1, "操作失败！");

    // 成员变量
    private int status;
    private String message;

    // 构造方法
    ResultStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
