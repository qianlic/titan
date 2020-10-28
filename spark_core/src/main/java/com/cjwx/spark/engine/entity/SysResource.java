package com.cjwx.spark.engine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 资源
 * @Author: qian li
 * @Date: 2018年04月03日 15:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysResource extends BaseEntity {

    private String resourceCode;

    private String resourceName;

    private String url;

    private String type;

    private String description;

    private String ico;

    private Integer level;

    private Integer parentId;

    @TableField(exist = false)
    private List<SysResource> children = new ArrayList<>();

}
