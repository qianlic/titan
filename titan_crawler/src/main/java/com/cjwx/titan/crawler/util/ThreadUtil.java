package com.cjwx.titan.crawler.util;

import uk.org.lidalia.slf4jext.Logger;
import uk.org.lidalia.slf4jext.LoggerFactory;

public class ThreadUtil {
	
	protected static final Logger logger = LoggerFactory.getLogger(ThreadUtil.class);
	
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			logger.error("Error occurred", e);
		}
	}
	
}
