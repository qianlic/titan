package com.cjwx.spark.engine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description: 角色
 * @Author: qian li
 * @Date: 2018年04月03日 15:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {

    private String roleCode;

    private String roleName;

    private String description;

    @TableField(exist = false)
    private List<SysResource> resources;

}
