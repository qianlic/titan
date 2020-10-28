package com.cjwx.spark.engine.util;

import com.cjwx.spark.engine.core.exception.ServiceException;

/**
 * @Description: 异常工具类
 * @Author: qian li
 * @Date: 2020/10/28 15:06
 */
public class ExceptionUtils {

    /**
     * 异常抛出
     */
    public static void throwError(String message) {
        throw new ServiceException(message);
    }

    /**
     * 异常抛出
     */
    public static void throwError(String message, Throwable e) {
        throw new ServiceException(message, e);
    }

}
