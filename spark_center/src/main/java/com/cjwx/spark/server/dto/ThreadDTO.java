package com.cjwx.spark.server.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年07月30日 15:49
 */
@Data
public class ThreadDTO extends BaseDTO {

    private String globalThreadId;
    private String name;
    private Integer priority;
    private Boolean daemon;
    private String state;
    private Long cpuTimeMillis;
    private Long userTimeMillis;
    private Boolean deadlocked;
    private List<String> stackTrace;

}
