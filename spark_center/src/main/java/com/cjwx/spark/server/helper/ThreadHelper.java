package com.cjwx.spark.server.helper;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.util.ProcessUtils;
import com.cjwx.spark.server.dto.ThreadDTO;
import com.cjwx.spark.server.util.MXBeanUtils;
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

    public static PageDTO<ThreadDTO> findThreadList(ThreadDTO thread, int start, int size) {
        ThreadMXBean threadMXBean = MXBeanUtils.getThreadMXBean();
        boolean cpuTimeEnabled = cpuTimeEnabled(threadMXBean);
        long[] deadlockedThreads = deadlockedThreads(threadMXBean);
        Map<Thread, StackTraceElement[]> stackTraces = Thread.getAllStackTraces();

        List<ThreadDTO> threadList = new ArrayList<>();
        int[] idx = {0};
        stackTraces.forEach((k, v) -> {
            long id = k.getId();
            boolean deadlocked = false;
            if (Arrays.binarySearch(deadlockedThreads, id) >= 0) {
                deadlocked = true;
            }
            if (thread.getState() != null && !k.getState().name().equals(thread.getState())) {
                return;
            } else if (thread.getDaemon() != null && k.isDaemon() != thread.getDaemon()) {
                return;
            } else if (thread.getDeadlocked() != null && deadlocked != thread.getDeadlocked()) {
                return;
            } else if (idx[0]++ < start || idx[0] > start + size) {
                return;
            }
            ThreadDTO dto = new ThreadDTO();
            dto.setId(k.getId());
            dto.setGlobalThreadId(ProcessUtils.getPID() + '_' + k.getId());
            dto.setName(k.getName());
            dto.setPriority(k.getPriority());
            dto.setDaemon(k.isDaemon());
            dto.setState(k.getState().name());
            dto.setStackTrace(Arrays.stream(v).map(StackTraceElement::toString).collect(Collectors.toList()));
            if (cpuTimeEnabled) {
                dto.setCpuTimeMillis(threadMXBean.getThreadCpuTime(id) / 1000000);
                dto.setUserTimeMillis(threadMXBean.getThreadUserTime(id) / 1000000);
            }
            dto.setDeadlocked(deadlocked);
            threadList.add(dto);
        });
        PageDTO<ThreadDTO> page = new PageDTO<>();
        page.setStart(start);
        page.setLimit(size);
        page.setTotal(idx[0]);
        page.setList(threadList);
        return page;
    }

    private static long[] deadlockedThreads(ThreadMXBean threadMXBean) {
        if (threadMXBean.isSynchronizerUsageSupported()) {
            return threadMXBean.findDeadlockedThreads();
        }
        return threadMXBean.findMonitorDeadlockedThreads();
    }

    private static boolean cpuTimeEnabled(ThreadMXBean threadMXBean) {
        return threadMXBean.isThreadCpuTimeSupported() && threadMXBean.isThreadCpuTimeEnabled();
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
