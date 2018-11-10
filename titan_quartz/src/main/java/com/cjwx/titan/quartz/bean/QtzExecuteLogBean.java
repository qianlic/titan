package com.cjwx.titan.quartz.bean;

import com.cjwx.titan.engine.core.base.bean.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:51
 */
@Data
@Entity
@Table(name = "qtz_execute_log")
public class QtzExecuteLogBean extends AbstractBean {

    @Column(name = "taskname")
    private String taskname;
    @Column(name = "taskgroup")
    private String taskgroup;
    @Column(name = "server")
    private String server;
    @Column(name = "path")
    private String path;
    @Column(name = "param")
    private String param;
    @Column(name = "success")
    private Boolean success;
    @Column(name = "message")
    private String message;
    @Column(name = "result")
    private String result;
    @Column(name = "executetime")
    private Date executetime;

}
