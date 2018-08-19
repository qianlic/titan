package com.cjwx.titan.crawler.crawler;

import com.cjwx.titan.crawler.WebCrawler;
import com.cjwx.titan.crawler.WebCrawlerFactory;

public class DefaultWebCrawlerFactory<T extends WebCrawler> implements WebCrawlerFactory<T> {

	private final Class<T> _c;
	
	public DefaultWebCrawlerFactory(Class<T> _c) {
		this._c = _c;
	}
	
	@Override
	public T newInstance() throws Exception {
		try {
			return _c.newInstance();
		} catch (ReflectiveOperationException e) {
			throw e;
		}
	}

}
