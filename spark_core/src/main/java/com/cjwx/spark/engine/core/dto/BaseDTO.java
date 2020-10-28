package com.cjwx.spark.engine.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: dto
 * @Author: qian li
 * @Date: 2020年09月11日 16:57
 */
@Data
public class BaseDTO implements Serializable {

    private Long id;

    private Boolean status;

    private Integer start;

    private Integer size;

}
