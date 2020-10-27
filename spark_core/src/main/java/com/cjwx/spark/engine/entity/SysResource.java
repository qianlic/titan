package com.cjwx.spark.engine.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 资源
 * @Author: qian li
 * @Date: 2018年04月03日 15:44
 */
@Data
public class SysResource extends AbstractEntity {

    private String resourceCode;

    private String resourceName;

    private String url;

    private String type;

    private String description;

    private String ico;

    private int level;

    private int parentId;

    private List<SysResource> children = new ArrayList<>();

}
