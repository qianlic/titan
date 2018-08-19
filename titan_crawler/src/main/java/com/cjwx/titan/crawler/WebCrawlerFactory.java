package com.cjwx.titan.crawler;

public interface WebCrawlerFactory<T extends WebCrawler> {
	
	T newInstance() throws Exception;
	
}
