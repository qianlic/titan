package com.cjwx.spark.quartz.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:51
 */
@Data
public class QtzExecuteLogDTO extends BaseDTO {

    private String taskName;

    private String taskGroup;

    private String server;

    private String path;

    private String param;

    private Boolean success;

    private String message;

    private String result;

    private Date executeTime;

}
