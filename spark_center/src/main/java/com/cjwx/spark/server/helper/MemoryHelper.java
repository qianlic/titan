package com.cjwx.spark.server.helper;

import com.cjwx.spark.server.dto.MemoryDTO;
import com.cjwx.spark.server.util.MXBeanUtils;
import lombok.extern.slf4j.Slf4j;

import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Set;

/**
 * Created by CJWX on 2016/4/18.
 */
@Slf4j
public class MemoryHelper implements Serializable {

    private static final String NEXT = ",\n";
    private static final String MO = " Mo";

    public static MemoryDTO getMemoryInformation() throws Exception {
        MemoryDTO memory = new MemoryDTO();
        Runtime runtime = Runtime.getRuntime();
        memory.setMaxMemory(runtime.maxMemory() / 1024 / 1024);
        memory.setTotalMemory(runtime.totalMemory() / 1024 / 1024);
        memory.setFreeMemory(runtime.freeMemory() / 1024 / 1024);
        memory.setUsedMemory(memory.getTotalMemory() - memory.getFreeMemory());
        MemoryUsage usage = permGenMemoryUsage();
        if (usage != null) {
            memory.setUsedPermGen(usage.getUsed());
            memory.setMaxPermGen(usage.getMax());
        }
        memory.setUsedNonHeapMemory(usedNonHeapMemory());
        memory.setUsedBufferedMemory(usedBufferMemory());
        memory.setLoadedClassesCount(loadedClassesCount());
        memory.setGarbageCollectionTimeMillis(garbageCollectionTimeMillis());

        if (MXBeanUtils.isSunOsMBean()) {
            memory.setTotalPhysicalMemorySize(totalPhysicalMemorySize());
            memory.setFreePhysicalMemorySize(freePhysicalMemorySize());
            memory.setTotalSwapSpaceSize(totalSwapSpaceSize());
            memory.setFreeSwapSpaceSize(freeSwapSpaceSize());
        }
        memory.setMemoryDetails(buildMemoryDetails(memory));
        return memory;
    }

    private static String buildMemoryDetails(MemoryDTO memory) throws Exception {
        StringBuilder osInfo = new StringBuilder();
        osInfo.append("Non heap memory = ")
                .append(memory.getUsedNonHeapMemory())
                .append(MO)
                .append(" (Perm Gen, Code Cache)");
        if (memory.getUsedBufferedMemory() >= 0) {
            osInfo.append(NEXT)
                    .append("Buffered memory = ")
                    .append(memory.getUsedBufferedMemory())
                    .append(MO);
        }
        osInfo.append(NEXT)
                .append("Loaded classes = ")
                .append(memory.getLoadedClassesCount())
                .append(NEXT)
                .append("Garbage collection time = ")
                .append(memory.getGarbageCollectionTimeMillis())
                .append(" ms");
        if (MXBeanUtils.isSunOsMBean()) {
            osInfo.append(NEXT + "Process cpu time = ")
                    .append(processCpuTime())
                    .append(" ms,\nCommitted virtual memory = ")
                    .append(committedVirtualMemorySize())
                    .append(MO)
                    .append(",\nFree physical memory = ")
                    .append(memory.getFreePhysicalMemorySize())
                    .append(MO)
                    .append(",\nTotal physical memory = ")
                    .append(memory.getTotalPhysicalMemorySize())
                    .append(MO)
                    .append(",\nFree swap space = ")
                    .append(memory.getFreeSwapSpaceSize())
                    .append(MO)
                    .append(",\nTotal swap space = ")
                    .append(memory.getTotalSwapSpaceSize())
                    .append(MO);
        }
        return osInfo.toString();
    }

    private static long totalPhysicalMemorySize() throws Exception {
        return MXBeanUtils.getFromOperatingSystem("getTotalPhysicalMemorySize") / 1024 / 1024;
    }

    private static long freePhysicalMemorySize() throws Exception {
        return MXBeanUtils.getFromOperatingSystem("getFreePhysicalMemorySize") / 1024 / 1024;
    }

    private static long totalSwapSpaceSize() throws Exception {
        return MXBeanUtils.getFromOperatingSystem("getTotalSwapSpaceSize") / 1024 / 1024;
    }

    private static long freeSwapSpaceSize() throws Exception {
        return MXBeanUtils.getFromOperatingSystem("getFreeSwapSpaceSize") / 1024 / 1024;
    }

    private static long processCpuTime() throws Exception {
        return MXBeanUtils.getFromOperatingSystem("getProcessCpuTime") / 1000000;
    }

    private static long committedVirtualMemorySize() throws Exception {
        return MXBeanUtils.getFromOperatingSystem("getCommittedVirtualMemorySize") / 1024 / 1024;
    }

    private static long garbageCollectionTimeMillis() {
        long garbageCollectionTime = 0;
        List<GarbageCollectorMXBean> garbageCollectors = MXBeanUtils.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollector : garbageCollectors) {
            garbageCollectionTime += garbageCollector.getCollectionTime();
        }
        return garbageCollectionTime;
    }

    private static int loadedClassesCount() {
        return MXBeanUtils.getClassLoadingMXBean().getLoadedClassCount();
    }

    private static long usedBufferMemory() throws Exception {
        Set<ObjectName> nioBufferPools = MXBeanUtils.queryName("java.nio:type=BufferPool,*");
        if (nioBufferPools.isEmpty()) {
            return -1;
        }
        long result = 0;
        for (ObjectName objectName : nioBufferPools) {
            result += (Long) MXBeanUtils.getAttribute(objectName, "MemoryUsed");
        }
        return result / 1024 / 1024;
    }

    private static Long usedNonHeapMemory() {
        return MXBeanUtils.getMemoryMXBean().getNonHeapMemoryUsage().getUsed() / 1024 / 1024;
    }

    private static MemoryUsage permGenMemoryUsage() {
        return MXBeanUtils.getMemoryPoolMXBeans().stream()
                .filter(memoryPool -> memoryPool.getName().endsWith("Perm Gen"))
                .findFirst()
                .map(MemoryPoolMXBean::getUsage)
                .orElse(null);
    }

}
