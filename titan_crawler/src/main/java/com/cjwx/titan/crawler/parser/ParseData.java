package com.cjwx.titan.crawler.parser;

import java.util.Set;

import com.cjwx.titan.crawler.url.WebURL;

public interface ParseData {

	Set<WebURL> getOutgoingUrls();

	void setOutgoingUrls(Set<WebURL> outgoingUrls);

	@Override
	String toString();
	
}
