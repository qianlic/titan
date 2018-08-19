package com.cjwx.titan.crawler.robotstxt;

import lombok.Data;

@Data
public class RobotstxtConfig {

	private boolean enabled = false;

	private String userAgentName = "xxk";

	private int cacheSize = 500;
	
}
