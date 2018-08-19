package com.cjwx.titan.shiro.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class RedisCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();
    private final int EXPIRE_TIME = 5 * 60 * 1000;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache<K, V> c = caches.get(name);
        if (c == null) {
            c = new RedisCache<>(name, EXPIRE_TIME);
            caches.put(name, c);
        }
        return c;
    }

}
