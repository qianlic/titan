package com.cjwx.spark.server.entity;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import com.cjwx.spark.engine.util.ProcessUtils;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年07月30日 15:49
 */
@Data
public class ThreadBean extends BaseDTO {

    private String globalThreadId;
    private String name;
    private int priority;
    private boolean daemon;
    private Thread.State state;
    private long cpuTimeMillis = -1;
    private long userTimeMillis = -1;
    private boolean deadlocked = false;
    private List<String> stackTrace;

    public ThreadBean(Thread thread) {
        this.setId(thread.getId());
        this.globalThreadId = ProcessUtils.getPID() + '_' + thread.getId();
        this.name = thread.getName();
        this.priority = thread.getPriority();
        this.daemon = thread.isDaemon();
        this.state = thread.getState();
    }

}
