package com.cjwx.spark.engine.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 列表页
 * @Author: qian li
 * @Date: 2020/10/28 14:19
 */
@Data
public class PageDTO<T> implements Serializable {

    private long start;

    private long limit;

    private long total;

    private List<T> list;

}
