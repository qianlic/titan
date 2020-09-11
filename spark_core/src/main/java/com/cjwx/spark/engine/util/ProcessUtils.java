package com.cjwx.spark.engine.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @Description: 进程操作工具类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
public class ProcessUtils {

    /**
     * 获取进程ID
     */
    public static String getPID() {
        String pid = System.getProperty("pid");
        if (pid == null) {
            final RuntimeMXBean rtb = ManagementFactory.getRuntimeMXBean();
            final String processName = rtb.getName();

            if (processName.indexOf('@') != -1) {
                pid = processName.substring(0, processName.indexOf('@'));
            } else {
                pid = getPIDFromOS();
            }
            System.setProperty("pid", pid);
        }
        return pid;
    }

    /**
     * 从操作系统获取进程ID
     */
    public static String getPIDFromOS() {
        String pid;
        try {
            final String[] cmd;
            File tempFile = null;
            Process process = null;
            try {
                if (!System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows")) {
                    cmd = new String[] { "/bin/sh", "-c", "echo $$ $PPID" };
                } else {
                    tempFile = File.createTempFile("getpids", ".exe");
                    cmd = new String[] { tempFile.getAbsolutePath() };
                }
                process = Runtime.getRuntime().exec(cmd);
                final ByteArrayOutputStream bout = new ByteArrayOutputStream();

                final StringTokenizer stok = new StringTokenizer(bout.toString());
                stok.nextToken();
                pid = stok.nextToken();

                process.waitFor();
            } finally {
                if (process != null) {
                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    process.destroy();
                }
                if (tempFile != null && !tempFile.delete()) {
                    tempFile.deleteOnExit();
                }
            }
        } catch (final InterruptedException | IOException e) {
            pid = e.toString();
        }
        return pid;
    }

    public static void threadSleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.error("Error occurred", e);
        }
    }

}
