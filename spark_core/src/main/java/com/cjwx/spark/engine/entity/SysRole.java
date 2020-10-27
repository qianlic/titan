package com.cjwx.spark.engine.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 角色
 * @Author: qian li
 * @Date: 2018年04月03日 15:41
 */
@Data
public class SysRole extends AbstractEntity {

    private String roleCode;

    private String roleName;

    private String description;

    private List<SysResource> resources;

}
