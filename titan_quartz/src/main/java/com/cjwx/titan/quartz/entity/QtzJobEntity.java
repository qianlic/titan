package com.cjwx.titan.quartz.entity;

import lombok.Data;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 15:02
 */
@Data
public class QtzJobEntity {

    private String name;
    private String group;
    private String service;
    private String method;
    private String data;
    private String description;
    private List<TriggerKey> triggerKeys;

    /**
     * 生成JobKey
     */
    public JobKey getJobKey() {
        return JobKey.jobKey(this.name, this.group);
    }

}
