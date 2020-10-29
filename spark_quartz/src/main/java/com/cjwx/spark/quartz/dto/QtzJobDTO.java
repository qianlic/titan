package com.cjwx.spark.quartz.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 15:02
 */
@Data
public class QtzJobDTO extends BaseDTO {

    private String jobName;
    private String jobGroup;
    private String server;
    private String path;
    private String data;
    private int priority;
    private String cronExpression;
    private Date previousTime;
    private Date nextTime;
    private String description;
    private String state;

}
