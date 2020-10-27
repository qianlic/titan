package com.cjwx.spark.engine.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: 用户
 * @Author: qian li
 * @Date: 2018年04月03日 15:29
 */
@Data
public class SysUser extends AbstractEntity {

    private String userCode;

    private String userName;

    private String type;

    private String password;

    private String salt;

    private String sex;

    private Date birthday;

    private String mobile;

    private String email;

    private String imgurl;

    private Date lastLoginTime;

}
