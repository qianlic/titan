package com.cjwx.spark.engine.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * @Description: 客户端
 * @Author: qian li
 * @Date: 2020年09月09日} 15:03
 */
@Data
@Entity
@Table(name = "sys_client")
public class SysClientEntity extends AbstractEntity {

    @Column(name = "client_code")
    private String clientCode;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

}
