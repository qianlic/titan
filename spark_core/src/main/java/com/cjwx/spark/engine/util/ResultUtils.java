package com.cjwx.spark.engine.util;

import com.cjwx.spark.engine.core.dto.ResultDTO;

/**
 * @Description: 返回结果
 * @Author: qian li
 * @Date: 2020/10/28 14:19
 */
public class ResultUtils {

    /**
     * 成功
     */
    public static <T> ResultDTO<T> success() {
        ResultDTO<T> result = new ResultDTO<>();
        result.setSuccess(true);
        result.setCode("0");
        result.setMessage("操作成功!");
        return result;
    }

    /**
     * 成功
     */
    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> result = success();
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> ResultDTO<T> fail(String message) {
        return fail("0001", message);
    }

    /**
     * 失败
     */
    public static <T> ResultDTO<T> fail(String code, String message) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
