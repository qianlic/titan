package com.cjwx.spark.engine.entity;

import lombok.Data;

/**
 * @Description: 客户端
 * @Author: qian li
 * @Date: 2020年09月09日} 15:03
 */
@Data
public class SysClient extends AbstractEntity {

    private String clientCode;

    private String clientName;

    private String clientSecret;

    private String scopes;

    private String authorizedGrantTypes;

}
