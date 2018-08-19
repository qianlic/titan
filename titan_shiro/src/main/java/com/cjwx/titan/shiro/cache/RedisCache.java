package com.cjwx.titan.shiro.cache;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.reids.util.RedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

/**
 * @Description: RedisCache
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private String cacheName;// 缓存名
    private long expireTime;// 缓存的超时时间，单位为s

    public RedisCache(String cacheName, long expireTime) {
        super();
        this.cacheName = cacheName + "." + HttpConstant.VERSION + ".";
        this.expireTime = expireTime;
    }

    /**
     * 通过key来获取对应的缓存对象
     */
    @Override
    public Object get(Object key) throws CacheException {
        return RedisUtils.get(cacheName + key);
    }

    /**
     * 将权限信息加入缓存中
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {
        RedisUtils.set(cacheName + key, value, this.expireTime);
        return value;
    }

    /**
     * 将权限信息从缓存中删除
     */
    @Override
    public Object remove(Object key) throws CacheException {
        RedisUtils.remove(cacheName + key);
        return key;
    }

    @Override
    public void clear() throws CacheException {
        RedisUtils.remove(cacheName);
    }

    @Override
    public int size() {
        return (int) RedisUtils.sizeHash(cacheName);
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    public static void removeAuthenticationCache(String key) {
        RedisUtils.remove("authenticationCache." + HttpConstant.VERSION + "." + key);
    }

}