package com.cjwx.titan.crawler.crawler;

import lombok.Data;

@Data
public abstract class Configurable {

	protected CrawlConfig config;

	protected Configurable(CrawlConfig config) {
		this.config = config;
	}
	
}