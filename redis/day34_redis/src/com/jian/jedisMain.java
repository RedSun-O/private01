package com.jian;

import com.jian.utils.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jian
 * @date 2021/1/14 11:22
 */
public class jedisMain {
    @Test
    public void test01(){
        String host = "localhost";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        jedis.set("username", "hello ok");
        jedis.close();
    }
    @Test
    public void test02(){
        String host = "localhost";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        String username = jedis.get("username");
        System.out.println(username);
        jedis.close();
    }
    @Test
    public void tset03(){
        String host = "localhost";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        Map<String, String> map = new HashMap<>();
        map.put("username", "rose");
        map.put("age", "18");
        map.put("address", "usa");
        map.put("sex", "fmale");
        String hkey3 = jedis.hmset("hkey3", map);
        System.out.println(hkey3);
        jedis.close();
    }
    @Test
    public void test05(){
        String host = "localhost";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        Long string = jedis.lpush("string", "a", "b", "c");
        System.out.println(string);
    }
    @Test
    public void test06(){
        String host = "localhost";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        Long string1 = jedis.sadd("string2", "a", "b", "c");
        System.out.println(string1);
    }
    @Test
    public void test07(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(12);
        jedisPoolConfig.setMaxWaitMillis(3000);
        String host = "localhost";
        int port = 6379;
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port);
        Jedis jedis = jedisPool.getResource();
        String username = jedis.get("username");
        System.out.println(username);
        jedis.close();

    }
    @Test
    public void test08(){
        Jedis jedis = JedisUtils.getJedis();
        String username = jedis.get("username");
        System.out.println(username);
        jedis.close();
    }
}
