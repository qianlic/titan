package com.cjwx.spark.engine.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: bean
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3587850931125773471L;

    private Long id;

    private Boolean status;

}
