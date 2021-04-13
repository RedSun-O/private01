package com.jian.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * @author Jian
 * @date 2021/1/14 13:09
 */
public class JedisUtils {
    private static JedisPool jedisPool;
    private static String host;
    private static Integer port;
    private static Integer maxTotal;
    private static Integer maxIdle;
    private static Integer maxWaitMillis;
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jedis");
        host = resourceBundle.getString("host");
         port = Integer.valueOf(resourceBundle.getString("port"));
         maxTotal = Integer.valueOf(resourceBundle.getString("maxTotal"));
         maxIdle = Integer.valueOf(resourceBundle.getString("maxIdle"));
         maxWaitMillis = Integer.valueOf(resourceBundle.getString("maxWaitMillis"));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
         jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
