package com.cjwx.titan.quartz.bean;

import com.cjwx.titan.engine.core.base.bean.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:51
 */
@Data
@Entity
@Table(name = "qtz_scheduler_log")
public class QtzScheduleLogBean extends AbstractBean {

    @Column(name = "jobname")
    private String jobname;
    @Column(name = "jobgroup")
    private String jobgroup;
    @Column(name = "server")
    private String server;
    @Column(name = "path")
    private String path;
    @Column(name = "data")
    private String data;
    @Column(name = "result")
    private String result;

}
