package com.cjwx.titan.crawler.fetcher;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 连接监控线程
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class IdleConnectionMonitorThread extends Thread {

	private final PoolingHttpClientConnectionManager connMgr;
	private volatile boolean shutdown;

	public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager connMgr) {
		super("Connection Manager");
		this.connMgr = connMgr;
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(5000);
					// 关闭过期的连接
					connMgr.closeExpiredConnections();
					// 关闭30分钟的连接
					connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
				}
			}
		} catch (InterruptedException ignored) {
			// terminate
		}
	}

	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}
}
