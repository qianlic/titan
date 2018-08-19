package com.cjwx.titan.crawler.robotstxt;


/**
 * @Description: 主机指令
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class HostDirectives {

    private static final long EXPIRATION_DELAY = 24 * 60 * 1000L;

    private final RuleSet disallows = new RuleSet();
    private final RuleSet allows = new RuleSet();

    private final long timeFetched = System.currentTimeMillis();
    private long timeLastAccessed;

    public boolean needsRefetch() {
        return ((System.currentTimeMillis() - timeFetched) > EXPIRATION_DELAY);
    }

    //判断路径是否允许访问
    public boolean allows(String path) {
        timeLastAccessed = System.currentTimeMillis();
        return !disallows.containsPrefixOf(path) || allows.containsPrefixOf(path);
    }

    public void addDisallow(String path) {
        disallows.add(path);
    }

    public void addAllow(String path) {
        allows.add(path);
    }

    public long getLastAccessTime() {
        return timeLastAccessed;
    }

}
