package com.cjwx.spark.engine.util;

import lombok.Data;

/**
 * @Description: 容器
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public final class Model<T> {

    private Integer start;

    private Integer size;

    private T params;

}
