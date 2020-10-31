package com.cjwx.spark.server.util;

import lombok.extern.slf4j.Slf4j;

import javax.management.ObjectName;
import java.lang.management.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by CJWX on 2016/4/18.
 */
@Slf4j
public class MXBeanUtils {

    public static boolean isSunOsMBean() {
        OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
        String className = bean.getClass().getName();
        return "com.sun.management.OperatingSystem".equals(className)
                || "com.sun.management.UnixOperatingSystem".equals(className)
                || "sun.management.OperatingSystemImpl".equals(className);
    }

    public static long getFromOperatingSystem(String methodName) throws Exception {
        //用于操作系统的管理接口，Java 虚拟机在此操作系统上运行
        OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
        Method method = bean.getClass().getMethod(methodName, (Class<?>[]) null);
        method.setAccessible(true);
        return (Long) method.invoke(bean, (Object) null);
    }

    public static ClassLoadingMXBean getClassLoadingMXBean() {
        //用于 Java 虚拟机的类加载系统的管理接口
        return ManagementFactory.getClassLoadingMXBean();
    }

    public static List<GarbageCollectorMXBean> getGarbageCollectorMXBeans() {
        //用于 Java 虚拟机的垃圾回收的管理接口
        return ManagementFactory.getGarbageCollectorMXBeans();
    }

    public static MemoryMXBean getMemoryMXBean() {
        //虚拟机的内存系统的管理接口
        return ManagementFactory.getMemoryMXBean();
    }

    public static List<MemoryPoolMXBean> getMemoryPoolMXBeans() {
        //内存池的管理接口
        return ManagementFactory.getMemoryPoolMXBeans();
    }

    public static ThreadMXBean getThreadMXBean() {
        //虚拟机线程系统的管理接口
        return ManagementFactory.getThreadMXBean();
    }

    public static Set<ObjectName> queryName(String name) throws Exception {
        return ManagementFactory.getPlatformMBeanServer()
                .queryNames(new ObjectName(name), null);
    }

    public static Object getAttribute(ObjectName name, String attribute) throws Exception {
        return ManagementFactory.getPlatformMBeanServer()
                .getAttribute(name, attribute);
    }

}
