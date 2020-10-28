package com.cjwx.spark.engine.entity;

import com.cjwx.spark.engine.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 客户端
 * @Author: qian li
 * @Date: 2020/10/28 13:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysClient extends BaseEntity {

    private String clientCode;

    private String clientName;

    private String clientSecret;

    private String scopes;

    private String authorizedGrantTypes;

}
