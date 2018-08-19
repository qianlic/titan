package com.cjwx.titan.monitor;

import lombok.extern.slf4j.Slf4j;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CJWX on 2016/4/18.
 */
@Slf4j
public class MBeans {

    private static MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
    //内存池的管理接口
    public static List<MemoryPoolMXBean> MEMORY_POOL_MXBEANS = ManagementFactory.getMemoryPoolMXBeans();
    //用于 Java 虚拟机的垃圾回收的管理接口
    public static List<GarbageCollectorMXBean> CARBAGE_COLLECTOR_MXBEANS = ManagementFactory.getGarbageCollectorMXBeans();
    //虚拟机的内存系统的管理接口
    public static MemoryMXBean MEMORY_MXBEAN = ManagementFactory.getMemoryMXBean();
    //虚拟机线程系统的管理接口
    public static ThreadMXBean THREAD_MXBEAN = ManagementFactory.getThreadMXBean();
    //用于 Java 虚拟机的类加载系统的管理接口
    public static ClassLoadingMXBean CLASSLOADING_MXBEAN = ManagementFactory.getClassLoadingMXBean();
    //用于操作系统的管理接口，Java 虚拟机在此操作系统上运行
    public static OperatingSystemMXBean OPERATING_SYSTEM_MXBEAN = ManagementFactory.getOperatingSystemMXBean();

    public static Set<ObjectName> getTomcatThreadPools() {
        return queryName("*:type=ThreadPool,*");
    }

    public static Set<ObjectName> getTomcatGlobalRequestProcessors() {
        return queryName("*:type=GlobalRequestProcessor,*");
    }

    public static Set<ObjectName> getNioBufferPools() {
        return queryName("java.nio:type=BufferPool,*");
    }

    public static long getFromOperatingSystem(String methodName) {
        try {
            Method method = OPERATING_SYSTEM_MXBEAN.getClass().getMethod(methodName, (Class<?>[]) null);
            method.setAccessible(true);
            return (Long) method.invoke(OPERATING_SYSTEM_MXBEAN, (Object[]) null);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return -1;
    }

    public static Set<ObjectName> queryName(String name) {
        try {
            return mbeanServer.queryNames(new ObjectName(name), null);
        } catch (MalformedObjectNameException e) {
            log.debug(e.getMessage(), e);
        }
        return null;
    }

    public static Object getAttribute(ObjectName name, String attribute) {
        try {
            return mbeanServer.getAttribute(name, attribute);
        } catch (JMException e) {
            log.debug(e.getMessage(), e);
        }
        return null;
    }

}
