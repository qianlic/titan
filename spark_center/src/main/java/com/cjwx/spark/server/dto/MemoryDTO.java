package com.cjwx.spark.server.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年07月30日 20:59
 */
@Data
public class MemoryDTO extends BaseDTO {

    private long maxMemory;//可占用内存

    private long totalMemory;//已占用内存

    private long freeMemory;//空闲内存

    private long usedMemory;//已使用内存

    //内存的永久保存区域
    private long usedPermGen = -1;

    private long maxPermGen = -1;
    //非堆内存的当前使用量
    private long usedNonHeapMemory;

    private long usedBufferedMemory;

    private int loadedClassesCount;//加载类的数量

    private long garbageCollectionTimeMillis;

    private long totalPhysicalMemorySize = -1;

    private long freePhysicalMemorySize = -1;

    private long totalSwapSpaceSize = -1;

    private long freeSwapSpaceSize = -1;

    private String memoryDetails;

}
