package com.cjwx.spark.server.helper;

import com.cjwx.spark.server.dto.TomcatDTO;
import com.cjwx.spark.server.util.MXBeanUtils;

import javax.management.ObjectName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by CJWX on 2016/4/18.
 */
public class TomcatHelper implements Serializable {

    private static boolean TOMCAT_USED = System.getProperty("catalina.home") != null;

    public static List<TomcatDTO> findTomcatList() throws Exception {
        List<TomcatDTO> tomcatList = new ArrayList<>();
        if (TOMCAT_USED) {
            Set<ObjectName> tomcatThreadPools = MXBeanUtils.queryName("*:type=ThreadPool,*");
            Set<ObjectName> tomcatProcessors = MXBeanUtils.queryName("*:type=GlobalRequestProcessor,*");
            Map<String, ObjectName> tomcatProcessorsMap = tomcatProcessors.stream()
                    .collect(Collectors.toMap(ObjectName
                            -> ObjectName.getKeyProperty("name"), Function.identity()));

            for (ObjectName threadPool : tomcatThreadPools) {
                String name = threadPool.getKeyProperty("name");
                TomcatDTO tomcat = new TomcatDTO();
                tomcat.setName(name);
                tomcat.setMaxThreads((Integer) MXBeanUtils.getAttribute(threadPool, "maxThreads"));
                tomcat.setCurrentThreadCount((Integer) MXBeanUtils.getAttribute(threadPool, "currentThreadCount"));
                tomcat.setCurrentThreadsBusy((Integer) MXBeanUtils.getAttribute(threadPool, "currentThreadsBusy"));

                ObjectName grp = tomcatProcessorsMap.get(name);
                if (grp != null) {
                    tomcat.setBytesReceived((Long) MXBeanUtils.getAttribute(grp, "bytesReceived"));
                    tomcat.setBytesSent((Long) MXBeanUtils.getAttribute(grp, "bytesSent"));
                    tomcat.setRequestCount((Integer) MXBeanUtils.getAttribute(grp, "requestCount"));
                    tomcat.setErrorCount((Integer) MXBeanUtils.getAttribute(grp, "errorCount"));
                    tomcat.setProcessingTime((Long) MXBeanUtils.getAttribute(grp, "processingTime"));
                    tomcat.setMaxTime((Long) MXBeanUtils.getAttribute(grp, "maxTime"));
                }
                tomcatList.add(tomcat);
            }
        }
        return tomcatList;
    }

}
