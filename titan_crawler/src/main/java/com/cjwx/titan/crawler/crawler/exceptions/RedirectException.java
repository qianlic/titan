package com.cjwx.titan.crawler.crawler.exceptions;

import uk.org.lidalia.slf4jext.Level;

public class RedirectException extends Exception {

	private static final long serialVersionUID = -2573547904153553119L;
	
	public Level level;

	public RedirectException(Level level, String msg) {
		super(msg);
		this.level = level;
	}
	
}
