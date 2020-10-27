package com.cjwx.spark.engine.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 抽象bean
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 3587850931125773471L;

    private Long id;

    private Boolean status = true;

}
