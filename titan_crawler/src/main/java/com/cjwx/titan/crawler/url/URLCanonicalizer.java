package com.cjwx.titan.crawler.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


//URL规范化
public class URLCanonicalizer {

	public static String getCanonicalURL(String url) {
		return getCanonicalURL(url, null);
	}
	
	public static String getCanonicalURL(String href, String context) {

		try {
			URL canonicalURL = new URL(UrlResolver.resolveUrl((context == null)?"":context,href));
			//获取URL域名
			String host = canonicalURL.getHost().toLowerCase();
			if (Objects.equals(host, "")) {
				return null;
			}
			//获取路径
			String path = canonicalURL.getPath();

			path = new URI(path.replace("\\", "/")).normalize().toString();

			int idx = path.indexOf("//");
			while (idx >= 0) {
				path = path.replace("//", "/");
				idx = path.indexOf("//");
			}

			while (path.startsWith("/../")) {
				path = path.substring(3);
			}

			path = path.trim();
			//获取请求参数
			final LinkedHashMap<String, String> params = createParameterMap(canonicalURL.getQuery());
			final String queryString;
			if ((params != null) && !params.isEmpty()) {
				String canonicalParams = canonicalize(params);
				queryString = (canonicalParams.isEmpty()?"":("?"+canonicalParams));
			} else {
				queryString = "";
			}

			if (path.isEmpty()) {
				path = "/";
			}

			int port = canonicalURL.getPort();
			if (port == canonicalURL.getDefaultPort()) {
				port = -1;
			}

			String protocol = canonicalURL.getProtocol().toLowerCase();
			String pathAndQueryString = normalizePath(path) + queryString;

			URL result = new URL(protocol, host, port, pathAndQueryString);
			return result.toExternalForm();

		} catch (MalformedURLException | URISyntaxException ex) {
			return null;
		}
	}

	private static LinkedHashMap<String, String> createParameterMap(
			final String queryString) {
		if ((queryString == null) || queryString.isEmpty()) {
			return null;
		}

		final String[] pairs = queryString.split("&");
		final Map<String, String> params = new LinkedHashMap<>(pairs.length);

		for (final String pair : pairs) {
			if (pair.isEmpty()) {
				continue;
			}

			String[] tokens = pair.split("=", 2);
			switch (tokens.length) {
			case 1:
				if (pair.charAt(0) == '=') {
					params.put("", tokens[0]);
				} else {
					params.put(tokens[0], "");
				}
				break;
			case 2:
				params.put(tokens[0], tokens[1]);
				break;
			}
		}
		return new LinkedHashMap<>(params);
	}

	private static String canonicalize(final LinkedHashMap<String, String> paramsMap) {
		if ((paramsMap == null) || paramsMap.isEmpty()) {
			return "";
		}

		final StringBuilder sb = new StringBuilder(100);
		for (Map.Entry<String, String> pair : paramsMap.entrySet()) {
			final String key = pair.getKey().toLowerCase();
			if ("jsessionid".equals(key)||"phpsessid".equals(key)||"aspsessionid".equals(key)) {
				continue;
			}
			if (sb.length() > 0) {
				sb.append('&');
			}
			sb.append(percentEncodeRfc3986(pair.getKey()));
			if (!pair.getValue().isEmpty()) {
				sb.append('=');
				sb.append(percentEncodeRfc3986(pair.getValue()));
			}
		}
		return sb.toString();
	}

	private static String percentEncodeRfc3986(String string) {
		try {
			string = string.replace("+", "%2B");
			string = URLDecoder.decode(string, "UTF-8");//将URL解码
			string = URLEncoder.encode(string, "UTF-8");//将URL转码
			return string.replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
		} catch (Exception e) {
			return string;
		}
	}

	private static String normalizePath(final String path) {
		return path.replace("%7E", "~").replace(" ", "%20");
	}
	
}
