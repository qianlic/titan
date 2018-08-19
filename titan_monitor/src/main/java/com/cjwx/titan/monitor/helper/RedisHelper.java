package com.cjwx.titan.monitor.helper;

import com.cjwx.titan.engine.reids.util.RedisUtils;
import com.cjwx.titan.engine.util.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.util.Slowlog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月01日 15:34
 */
public class RedisHelper {

    private static Jedis jedis;

    // 获取redis 服务器信息
    public static Map<String, Object> getRedisInfo() {
        Client client = getJedis().getClient();
        client.info();
        Map<String, Object> info = new HashMap<>();
        StringUtils.stringToList(client.getBulkReply(), "# ").stream()
                .filter(StringUtils::isNotEmpty)
                .forEach(a -> {
                    String[] key = {"Redis"};
                    Map<String, String> item = new HashMap<>();
                    StringUtils.stringToList(a, "\r\n").stream()
                            .filter(StringUtils::isNotEmpty)
                            .forEach(b -> {
                                String[] c = b.split(":", 2);
                                if (c.length == 1) {
                                    key[0] = key[0] + c[0];
                                } else if (c.length > 1) {
                                    item.put(c[0], c[1]);
                                }
                            });
                    info.put(key[0], item);
                });
        return info;
    }

    // 获取日志列表
    public static List<Slowlog> getLogs(long entries) {
        return getJedis().slowlogGet(entries);
    }

    // 获取日志条数
    public static Long getLogsLen() {
        return getJedis().slowlogLen();
    }

    // 清空日志
    public static String logEmpty() {
        return getJedis().slowlogReset();
    }

    // 获取占用内存大小
    public static Long dbSize() {
        Client client = getJedis().getClient();
        client.dbSize();
        return client.getIntegerReply();
    }

    private static Jedis getJedis() {
        if (jedis == null) {
            jedis = (Jedis) RedisUtils.getRedisConnection().getNativeConnection();
        }
        return jedis;
    }

}
