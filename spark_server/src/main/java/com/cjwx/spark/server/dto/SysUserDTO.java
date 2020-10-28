package com.cjwx.spark.server.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月11日 16:19
 */
@Data
public class SysUserDTO extends BaseDTO {

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

    private List<SysRoleDTO> roles;

    private List<SysResourceDTO> resources;

}
