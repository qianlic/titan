package com.cjwx.spark.engine.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 返回DTO
 * @Author: qian li
 * @Date: 2020/10/28 14:17
 */
@Data
public class ResultDTO<T> implements Serializable {

    private boolean success;

    private String code;

    private String message;

    private T data;

}
