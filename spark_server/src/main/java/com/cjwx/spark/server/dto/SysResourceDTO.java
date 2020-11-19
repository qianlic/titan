package com.cjwx.spark.server.dto;

import com.cjwx.spark.engine.dto.BaseDTO;
import com.cjwx.spark.engine.entity.SysResourceEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月11日 16:49
 */
@Data
public class SysResourceDTO extends BaseDTO {

    private String resourceCode;

    private String resourceName;

    private String url;

    private String type;

    private String description;

    private String ico;

    private int level;

    private int parentId;

    private List<SysResourceEntity> children = new ArrayList<>();

}
