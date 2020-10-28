package com.cjwx.spark.engine.core.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 树结构
 * @Author: qian li
 * @Date: 2020/10/28 14:21
 */
@Data
public class TreeNodeDTO {

    private Long id;

    private String nodeCode;

    private String nodeName;

    private String type;

    private List<TreeNodeDTO> children = new ArrayList<>();

    public String getLabel() {
        return this.nodeName + "[" + this.nodeCode + "]";
    }

}
