package com.cjwx.titan.crawler.crawler.exceptions;

public class NotAllowedContentException extends Exception {

	private static final long serialVersionUID = -8872601303716834169L;

	public NotAllowedContentException() {
		super("Not allowed to parse this type of content");
	}
	
}
