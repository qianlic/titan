package com.cjwx.spark.server.helper;

import com.cjwx.spark.engine.util.ObjectUtils;
import com.cjwx.spark.server.MBeans;
import com.cjwx.spark.server.entity.ThreadBean;
import lombok.Data;

import java.io.Serializable;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by CJWX on 2016/4/18.
 */
@Data
public class ThreadHelper implements Serializable {

    public static PageList<ThreadBean> findThreadList(int start, int size, Map<String, Object> wheres) {
        ThreadMXBean threadMXBean = MBeans.THREAD_MXBEAN;
        boolean cpuTimeEnabled = threadMXBean.isThreadCpuTimeSupported()
                && threadMXBean.isThreadCpuTimeEnabled();

        Map<Thread, StackTraceElement[]> stackTraces = Thread.getAllStackTraces();
        long[] deadlockedThreads;
        if (threadMXBean.isSynchronizerUsageSupported()) {
            deadlockedThreads = threadMXBean.findDeadlockedThreads();
        } else {
            deadlockedThreads = threadMXBean.findMonitorDeadlockedThreads();
        }
        List<ThreadBean> threadList = new ArrayList<>();
        int[] idx = {0};
        stackTraces.forEach((k, v) -> {
            Long id = k.getId();
            boolean deadlocked = false;
            if (deadlockedThreads != null && Arrays.binarySearch(deadlockedThreads, id) >= 0) {
                deadlocked = true;
            }
            if (wheres.containsKey("state") && !k.getState().name().equals(wheres.get("state"))) {
                return;
            } else if (wheres.containsKey("daemon") && k.isDaemon() != ObjectUtils.objectToBoolean(wheres.get("daemon"))) {
                return;
            } else if (wheres.containsKey("deadlocked") && deadlocked != ObjectUtils.objectToBoolean(wheres.get("deadlocked"))) {
                return;
            } else if (idx[0]++ < start || idx[0] > start + size) {
                return;
            }
            ThreadBean thread = new ThreadBean(k);
            thread.setStackTrace(Arrays.asList(v).stream().map(StackTraceElement::toString).collect(Collectors.toList()));
            if (cpuTimeEnabled) {
                thread.setCpuTimeMillis(threadMXBean.getThreadCpuTime(id) / 1000000);
                thread.setUserTimeMillis(threadMXBean.getThreadUserTime(id) / 1000000);
            }
            thread.setDeadlocked(deadlocked);
            threadList.add(thread);
        });
        PageList<ThreadBean> pageList = new PageList<>(start, size);
        pageList.setTotal(idx[0]);
        pageList.setList(threadList);
        return pageList;
    }

    public static String[][] findThreadList(ThreadBean wheres) {
        ThreadMXBean threadMXBean = MBeans.THREAD_MXBEAN;
        boolean cpuTimeEnabled = threadMXBean.isThreadCpuTimeSupported()
                && threadMXBean.isThreadCpuTimeEnabled();

        Map<Thread, StackTraceElement[]> stackTraces = Thread.getAllStackTraces();
        long[] deadlockedThreads;
        if (threadMXBean.isSynchronizerUsageSupported()) {
            deadlockedThreads = threadMXBean.findDeadlockedThreads();
        } else {
            deadlockedThreads = threadMXBean.findMonitorDeadlockedThreads();
        }
        List<ThreadBean> threadList = new ArrayList<>();
        stackTraces.forEach((k, v) -> {
            long id = k.getId();
            boolean deadlocked = false;
            if (deadlockedThreads != null && Arrays.binarySearch(deadlockedThreads, id) >= 0) {
                deadlocked = true;
            }
            /*if (wheres.getState() != null && !k.getState().equals(wheres.getState())) {
                return;
            } else if (wheres.isDaemon() && k.isDaemon() != ObjectUtils.objectToBoolean(wheres.get("daemon"))) {
                return;
            } else if (wheres.containsKey("deadlocked") && deadlocked != ObjectUtils.objectToBoolean(wheres.get("deadlocked"))) {
                return;
            }*/
            ThreadBean thread = new ThreadBean(k);
            thread.setStackTrace(Arrays.asList(v).stream().map(StackTraceElement::toString).collect(Collectors.toList()));
            if (cpuTimeEnabled) {
                thread.setCpuTimeMillis(threadMXBean.getThreadCpuTime(id) / 1000000);
                thread.setUserTimeMillis(threadMXBean.getThreadUserTime(id) / 1000000);
            }
            thread.setDeadlocked(deadlocked);
            threadList.add(thread);
        });
        String[][] data = new String[threadList.size()][];
        for (int i = 0; i < threadList.size(); i++) {
            data[i] = new String[2];
            data[i][0] = String.valueOf(threadList.get(i).getId());
            data[i][1] = threadList.get(i).getName();
        }
        return data;
    }

    public static int interruptThread(List<Long> ids) {
        final Map<Thread, StackTraceElement[]> stackTraces = Thread.getAllStackTraces();
        int count = 0;
        for (Thread thread : stackTraces.keySet()) {
            if (ids.contains(thread.getId())) {
                thread.interrupt();
                count++;
            }
        }
        return count;
    }

}
