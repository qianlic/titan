package com.cjwx.spark.server.helper;

import com.cjwx.spark.server.MBeans;
import com.cjwx.spark.server.entity.TomcatBean;

import javax.management.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by CJWX on 2016/4/18.
 */
public class TomcatHelper implements Serializable {

    private static boolean TOMCAT_USED = System.getProperty("catalina.home") != null;
    private static List<ObjectName> THREAD_POOLS = new ArrayList<>();
    private static Map<String, ObjectName> GLOBAL_REQUEST_PROCESSORS = new HashMap<>();

    public static List<TomcatBean> findTomcatList() {
        if (TOMCAT_USED) {
            synchronized (THREAD_POOLS) {
                if (THREAD_POOLS.isEmpty() || GLOBAL_REQUEST_PROCESSORS.isEmpty()) {
                    initMBeans();
                }
            }
            List<TomcatBean> tomcatList = new ArrayList<>();

            for (ObjectName threadPool : THREAD_POOLS) {
                String name = threadPool.getKeyProperty("name");
                TomcatBean tomcat = new TomcatBean();
                tomcat.setName(name);
                tomcat.setMaxThreads((Integer) MBeans.getAttribute(threadPool, "maxThreads"));
                tomcat.setCurrentThreadCount((Integer) MBeans.getAttribute(threadPool, "currentThreadCount"));
                tomcat.setCurrentThreadsBusy((Integer) MBeans.getAttribute(threadPool, "currentThreadsBusy"));

                ObjectName grp = GLOBAL_REQUEST_PROCESSORS.get(name);
                if (grp != null) {
                    tomcat.setBytesReceived((Long) MBeans.getAttribute(grp, "bytesReceived"));
                    tomcat.setBytesSent((Long) MBeans.getAttribute(grp, "bytesSent"));
                    tomcat.setRequestCount((Integer) MBeans.getAttribute(grp, "requestCount"));
                    tomcat.setErrorCount((Integer) MBeans.getAttribute(grp, "errorCount"));
                    tomcat.setProcessingTime((Long) MBeans.getAttribute(grp, "processingTime"));
                    tomcat.setMaxTime((Long) MBeans.getAttribute(grp, "maxTime"));
                }
                tomcatList.add(tomcat);
            }
            return tomcatList;
        }
        return null;
    }

    public static void initMBeans() {
        THREAD_POOLS.clear();
        THREAD_POOLS.addAll(MBeans.getTomcatThreadPools());

        GLOBAL_REQUEST_PROCESSORS.clear();
        GLOBAL_REQUEST_PROCESSORS.putAll(MBeans.getTomcatGlobalRequestProcessors().stream()
                .collect(Collectors.toMap(ObjectName -> ObjectName.getKeyProperty("name"), ObjectName -> ObjectName)));
    }

}
