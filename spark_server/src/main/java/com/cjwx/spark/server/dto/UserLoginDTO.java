package com.cjwx.spark.server.dto;

import lombok.Data;

/**
 * @Description: 登陆信息
 * @Author: qian li
 * @Date: 2020/10/30 15:46
 */
@Data
public class UserLoginDTO {

    private String userCode;

    private String userName;

    private String sex;

    private String imgUrl;

    private String token;

}
