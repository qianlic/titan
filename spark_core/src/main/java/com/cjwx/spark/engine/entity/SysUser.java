package com.cjwx.spark.engine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cjwx.spark.engine.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @Description: 用户
 * @Author: qian li
 * @Date: 2018年04月03日 15:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    private String userCode;

    private String userName;

    private String type;

    private String password;

    private String salt;

    private String sex;

    private Date birthday;

    private String mobile;

    private String email;

    private String imgUrl;

    private Date lastLoginTime;

    @TableField(exist = false)
    private List<SysRole> roles;

}
