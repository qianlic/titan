package com.cjwx.spark.server.helper;

import com.cjwx.spark.server.MBeans;
import com.cjwx.spark.server.entity.MemoryBean;
import lombok.extern.slf4j.Slf4j;

import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;
import java.util.Set;

/**
 * Created by CJWX on 2016/4/18.
 */
@Slf4j
public class MemoryHelper implements Serializable {

    private static final String NEXT = ",\n";
    private static final String MO = " Mo";

    public static MemoryBean getMemoryInformations() {
        MemoryBean memory = new MemoryBean(Runtime.getRuntime());
        MemoryUsage usage = getPermGenMemoryUsage();
        if (usage != null) {
            memory.setUsedPermGen(usage.getUsed());
            memory.setMaxPermGen(usage.getMax());
        }
        memory.setUsedNonHeapMemory(getNonHeapMemoryUsage().getUsed() / 1024 / 1024);
        memory.setUsedBufferedMemory(getUsedBufferMemory() / 1024 / 1024);
        memory.setLoadedClassesCount(MBeans.CLASSLOADING_MXBEAN.getLoadedClassCount());
        memory.setGarbageCollectionTimeMillis(buildGarbageCollectionTimeMillis());

        OperatingSystemMXBean operatingSystem = MBeans.OPERATING_SYSTEM_MXBEAN;
        if (isSunOsMBean(operatingSystem)) {
            memory.setTotalPhysicalMemorySize(MBeans.getFromOperatingSystem("getTotalPhysicalMemorySize") / 1024 / 1024);
            memory.setFreePhysicalMemorySize(MBeans.getFromOperatingSystem("getFreePhysicalMemorySize") / 1024 / 1024);
            memory.setTotalSwapSpaceSize(MBeans.getFromOperatingSystem("getTotalSwapSpaceSize") / 1024 / 1024);
            memory.setFreeSwapSpaceSize(MBeans.getFromOperatingSystem("getFreeSwapSpaceSize") / 1024 / 1024);
        }
        memory.setMemoryDetails(buildMemoryDetails(memory));
        return memory;
    }

    private static String buildMemoryDetails(MemoryBean memory) {
        String nonHeapMemory = "Non heap memory = " + memory.getUsedNonHeapMemory() + MO + " (Perm Gen, Code Cache)";
        String osInfo = nonHeapMemory;
        String classLoading = "Loaded classes = " + memory.getLoadedClassesCount();
        String gc = "Garbage collection time = " + memory.getGarbageCollectionTimeMillis() + " ms";
        if (memory.getUsedBufferedMemory() >= 0) {
            String bufferedMemory = "Buffered memory = " + memory.getUsedBufferedMemory();
            osInfo += NEXT + bufferedMemory + MO;
        }
        osInfo += NEXT + classLoading + NEXT + gc;
        if (isSunOsMBean(MBeans.OPERATING_SYSTEM_MXBEAN)) {
            osInfo += NEXT + "Process cpu time = "
                    + MBeans.getFromOperatingSystem("getProcessCpuTime") / 1000000
                    + " ms,\nCommitted virtual memory = "
                    + MBeans.getFromOperatingSystem("getCommittedVirtualMemorySize") / 1024 / 1024 + MO
                    + ",\nFree physical memory = " + memory.getFreePhysicalMemorySize() + MO
                    + ",\nTotal physical memory = " + memory.getTotalPhysicalMemorySize() + MO
                    + ",\nFree swap space = " + memory.getFreeSwapSpaceSize() + MO
                    + ",\nTotal swap space = " + memory.getTotalSwapSpaceSize() + MO;
        }
        return osInfo;
    }

    private static boolean isSunOsMBean(OperatingSystemMXBean operatingSystem) {
        String className = operatingSystem.getClass().getName();
        return "com.sun.management.OperatingSystem".equals(className)
                || "com.sun.management.UnixOperatingSystem".equals(className)
                || "sun.management.OperatingSystemImpl".equals(className);
    }

    private static long buildGarbageCollectionTimeMillis() {
        long garbageCollectionTime = 0;
        for (GarbageCollectorMXBean garbageCollector : MBeans.CARBAGE_COLLECTOR_MXBEANS) {
            garbageCollectionTime += garbageCollector.getCollectionTime();
        }
        return garbageCollectionTime;
    }

    private static long getUsedBufferMemory() {
        Set<ObjectName> nioBufferPools = MBeans.getNioBufferPools();
        if (nioBufferPools.isEmpty()) {
            return -1;
        }
        long result = 0;
        for (ObjectName objectName : nioBufferPools) {
            result += (Long) MBeans.getAttribute(objectName, "MemoryUsed");
        }
        return result;
    }

    public static MemoryUsage getNonHeapMemoryUsage() {
        return MBeans.MEMORY_MXBEAN.getNonHeapMemoryUsage();
    }

    public static MemoryUsage getPermGenMemoryUsage() {
        Optional<MemoryPoolMXBean> optional = MBeans.MEMORY_POOL_MXBEANS.stream()
                .filter(memoryPool -> memoryPool.getName().endsWith("Perm Gen"))
                .findFirst();
        if (optional.isPresent()) {
            return optional.get().getUsage();
        }
        return null;
    }

}
