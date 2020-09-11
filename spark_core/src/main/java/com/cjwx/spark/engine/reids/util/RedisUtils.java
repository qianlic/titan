package com.cjwx.spark.engine.reids.util;

import com.cjwx.spark.engine.web.helper.ApplicationContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 基于spring和redis的redisTemplate工具类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    public static RedisTemplate<String, Object> getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHelper.getBean("redisTemplate", RedisTemplate.class);
        }
        return redisTemplate;
    }

    public static RedisConnection getRedisConnection() {
        return getRedisTemplate().getConnectionFactory().getConnection();
    }

    /**
     * 缓存放入
     */
    public static void set(String k, Object v) {
        opsForValue().set(k, v);
    }

    /**
     * 缓存放入并设置过期时间
     */
    public static void set(String k, Object v, long t) {
        opsForValue().set(k, v, t, TimeUnit.SECONDS);
    }

    /**
     * 批量放入
     */
    public static void set(Map<String, Object> kvs) {
        opsForValue().multiSet(kvs);
    }

    /**
     * 缓存LIST放入,返回LIST长度
     */
    public static long setList(String k, Object v) {
        return opsForList().leftPush(k, v);
    }

    /**
     * 缓存LIST批量放入,返回LIST长度
     */
    public static long setList(String k, List<Object> vs) {
        return opsForList().leftPushAll(k, vs);
    }

    /**
     * 缓存HASH表放入
     */
    public static void setHash(String k, String hk, Object hv) {
        opsForHash().put(k, hk, hv);
    }

    /**
     * 缓存HASH表放入
     */
    public static void setHash(String k, Map<String, Object> v) {
        opsForHash().putAll(k, v);
    }

    /**
     * 缓存获取
     */
    public static Object get(String k) {
        return opsForValue().get(k);
    }

    /**
     * 批量获取
     */
    public static List<Object> get(List<String> ks) {
        return opsForValue().multiGet(ks);
    }

    /**
     * 缓存LIST值获取
     */
    public static List<Object> getList(String k) {
        return opsForList().range(k, 0, -1);
    }

    public static List<Object> getList(String k, long start, long end) {
        return opsForList().range(k, start, end);
    }

    /**
     * 缓存LIST值取出并删除
     */
    public static Object getOutList(String k) {
        return opsForList().rightPop(k);
    }

    /**
     * 缓存LIST值取出并放入另一个LIST
     */
    public static Object getOutAndInList(String source, String target) {
        return opsForList().rightPopAndLeftPush(source, target);
    }

    /**
     * 缓存HASH表获取
     */
    public static Map<String, Object> getHash(String k) {
        return opsForHash().entries(k);
    }

    /**
     * 缓存HASH表值获取
     */
    public static Object getHash(String k, String hk) {
        return opsForHash().get(k, hk);
    }

    /**
     * 批量缓存HASH表值获取
     */
    public static Object getHash(String k, List<String> hks) {
        return opsForHash().get(k, hks);
    }

    /**
     * 删除缓存
     */
    public static void remove(String k) {
        getRedisTemplate().delete(k);
    }

    /**
     * 批量删除缓存
     */
    public static void remove(List<String> ks) {
        getRedisTemplate().delete(ks);
    }

    /**
     * 删除LIST缓存,n个
     */
    public static void remove(String k, long n) {
        opsForList().trim(k, n, -1);
    }

    /**
     * 删除HASH表缓存
     */
    public static void remove(String k, String hk) {
        opsForHash().delete(k, hk);
    }

    /**
     * 批量删除HASH表缓存
     */
    public static void remove(String k, List<String> hks) {
        opsForHash().delete(k, hks);
    }

    /**
     * 缓存HASH表大小
     */
    public static long sizeList(String k) {
        return opsForList().size(k);
    }

    /**
     * 缓存HASH表大小
     */
    public static long sizeHash(String k) {
        return opsForHash().size(k);
    }

    /**
     * 指定缓存失效时间
     */
    public static void expire(String k, long t) {
        getRedisTemplate().expire(k, t, TimeUnit.SECONDS);
    }

    /**
     * 获取过期时间
     */
    public static long getExpire(String k) {
        return getRedisTemplate().getExpire(k, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在HASH表
     */
    public static boolean exists(String k, String hk) {
        return opsForHash().hasKey(k, hk);
    }

    /**
     * 判断key是否存在
     */
    public static boolean exists(String k) {
        return getRedisTemplate().hasKey(k);
    }

    /**
     * 操作list
     * 一个链表，链表上的每个节点都包含了一个字符串
     * 从链表的两端推入或者弹出元素；根据偏移量对链表进行修剪(trim)；读取单个或者多个元素；根据值来查找或者移除元素
     */
    public static ListOperations<String, Object> opsForList() {
        return getRedisTemplate().opsForList();
    }

    /**
     * 操作set
     * 包含字符串的无序收集器(unorderedcollection)，并且被包含的每个字符串都是独一无二的、各不相同
     * 添加、获取、移除单个元素；检查一个元素是否存在于某个集合中；计算交集、并集、差集；从集合里卖弄随机获取元素
     */
    public static SetOperations<String, Object> opsForSet() {
        return getRedisTemplate().opsForSet();
    }

    /**
     * 操作有序set
     * 字符串成员(member)与浮点数分值(score)之间的有序映射，元素的排列顺序由分值的大小决定
     * 添加、获取、删除单个元素；根据分值范围(range)或者成员来获取元素
     */
    public static ZSetOperations<String, Object> opsForZSet() {
        return getRedisTemplate().opsForZSet();
    }

    /**
     * 操作字符串
     * 可以是字符串、整数或者浮点数
     * 对整个字符串或者字符串的其中一部分执行操作；对象和浮点数执行自增(increment)或者自减(decrement)
     */
    public static ValueOperations<String, Object> opsForValue() {
        return getRedisTemplate().opsForValue();
    }

    /**
     * 操作hash
     * 包含键值对的无序散列表
     * 添加、获取、移除单个键值对；获取所有键值对
     */
    public static HashOperations<String, String, Object> opsForHash() {
        return getRedisTemplate().opsForHash();
    }

}