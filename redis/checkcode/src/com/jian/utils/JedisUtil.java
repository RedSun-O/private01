package com.jian.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * 包名:com.itheima.utils
 *
 * @author Leevi
 * 日期2020-07-26  14:36
 * Jedis的工具类
 */
public class JedisUtil {
    private static JedisPool jedisPool;
    //主机
    private static String host;
    //端口
    private static int port;
    //最大总数
    private static int maxTotal;
    //最大id
    private static int maxIdle;
    //最大等待时间
    private static int maxWaitMillis;
    static {
        //读取jedis.properties配置文件
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jedis");
        host = resourceBundle.getString("host");
        port = Integer.parseInt(resourceBundle.getString("port"));
        maxTotal = Integer.parseInt(resourceBundle.getString("maxTotal"));
        maxIdle = Integer.parseInt(resourceBundle.getString("maxIdle"));
        maxWaitMillis = Integer.parseInt(resourceBundle.getString(
                "maxWaitMillis"));

        //创建连接池
        //创建连接池的配置对象，进行一些属性的配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        //使用jedis连接池
        jedisPool = new JedisPool(jedisPoolConfig,host,port);
    }

    /**
     * 获取连接的方法
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
