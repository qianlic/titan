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
public class QtzTriggerEntity {

    private String name;
    private String group;
    private int priority;
    private String cronExpression;
    private String description;
    private String state;
    private JobKey jobKey;

    /**
     * 生成TriggerKey
     */
    public TriggerKey getTriggerKey() {
        return TriggerKey.triggerKey(this.name, this.group);
    }

}
