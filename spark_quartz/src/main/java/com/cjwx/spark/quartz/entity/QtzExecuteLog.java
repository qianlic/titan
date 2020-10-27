package com.cjwx.spark.quartz.entity;

import com.cjwx.spark.engine.entity.AbstractEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:51
 */
@Data
public class QtzExecuteLog extends AbstractEntity {

    private String taskname;

    private String taskgroup;

    private String server;

    private String path;

    private String param;

    private Boolean success;

    private String message;

    private String result;

    private Date executetime;

}
