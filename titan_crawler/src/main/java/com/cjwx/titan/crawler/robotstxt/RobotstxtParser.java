package com.cjwx.titan.crawler.robotstxt;

import java.util.StringTokenizer;

//解析robots.txt，获取允许及禁止访问的路径
public class RobotstxtParser {

	private static final String PATTERNS_USERAGENT = "(?i)^User-agent:.*";
	private static final String PATTERNS_DISALLOW = "(?i)Disallow:.*";
	private static final String PATTERNS_ALLOW = "(?i)Allow:.*";

	private static final int PATTERNS_USERAGENT_LENGTH = 11;
	private static final int PATTERNS_DISALLOW_LENGTH = 9;
	private static final int PATTERNS_ALLOW_LENGTH = 6;

	public static HostDirectives parse(String content, String myUserAgent) {
		HostDirectives directives = null;
		boolean inMatchingUserAgent = false;
		StringTokenizer st = new StringTokenizer(content, "\n\r");
		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			//判断是否为命令
			int commentIndex = line.indexOf('#');
			if (commentIndex > -1) {
				line = line.substring(0, commentIndex);
			}
			line = line.replaceAll("<[^>]+>", "");
			line = line.trim();
			if (line.isEmpty()) {
				continue;
			}
			//判断是否为User-agent
			if (line.matches(PATTERNS_USERAGENT)) {
				//获取User-agent值
				String ua = line.substring(PATTERNS_USERAGENT_LENGTH).trim().toLowerCase();
				inMatchingUserAgent = "*".equals(ua)||ua.contains(myUserAgent);//判断myUserAgent是否有授权
			} else if (line.matches(PATTERNS_DISALLOW)) {
				//添加禁止路径
				if (!inMatchingUserAgent) {
					continue;
				}
				String path = line.substring(PATTERNS_DISALLOW_LENGTH).trim();
				if (path.endsWith("*")) {
					path = path.substring(0, path.length() - 1);
				}
				path = path.trim();
				if (!path.isEmpty()) {
					if (directives == null) {
						directives = new HostDirectives();
					}
					directives.addDisallow(path);
				}
			} else if (line.matches(PATTERNS_ALLOW)) {
				//添加允许路径
				if (!inMatchingUserAgent) {
					continue;
				}
				String path = line.substring(PATTERNS_ALLOW_LENGTH).trim();
				if (path.endsWith("*")) {
					path = path.substring(0, path.length() - 1);
				}
				path = path.trim();
				if (directives == null) {
					directives = new HostDirectives();
				}
				directives.addAllow(path);
			}
		}
		return directives;
	}
	
}
