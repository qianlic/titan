package com.cjwx.spark.server.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月11日 16:47
 */
@Data
public class SysRoleDTO  extends BaseDTO {

    private String roleCode;

    private String roleName;

    private String description;

    private List<SysResourceDTO> resources;

}
