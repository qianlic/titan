package com.cjwx.titan.crawler.parser;

import com.cjwx.titan.crawler.url.WebURL;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TextParseData implements ParseData {

	private String textContent;
	private Set<WebURL> outgoingUrls = new HashSet<>();

	@Override
	public String toString() {
		return textContent;
	}
	
}
