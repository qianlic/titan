package com.cjwx.spark.server.entity;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月01日 13:12
 */
@Data
public class TomcatBean extends BaseDTO {

    private String name;
    private int maxThreads;
    private int currentThreadCount;
    private int currentThreadsBusy;
    private long bytesReceived = 0;
    private long bytesSent = 0;
    private int requestCount = 0;
    private int errorCount = 0;
    private long processingTime = 0;
    private long maxTime = 0;

}
