package com.cjwx.titan.engine.core.exception;

/**
 * @Description: 自定义异常处理
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }

}
