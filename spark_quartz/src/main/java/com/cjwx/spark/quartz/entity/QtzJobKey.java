package com.cjwx.spark.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020/10/29 11:26
 */
@Data
@TableName("qtz_job_details")
public class QtzJobKey {

    private String jobName;

    private String jobGroup;

}
