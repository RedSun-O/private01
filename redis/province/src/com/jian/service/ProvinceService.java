package com.jian.service;

import com.alibaba.fastjson.JSON;
import com.jian.dao.ProvinceDao;
import com.jian.pojo.Province;
import com.jian.utils.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jian
 * @date 2021/1/14 16:21
 */
public class ProvinceService {
    private ProvinceDao provinceDao = new ProvinceDao();
    public List<Province> findAll() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        String province_list = jedis.get("province_list");
        if (province_list == null) {
            List<Province> provinceList = provinceDao.findAll();
            province_list = JSON.toJSONString(provinceList);
            jedis.set("province_list", province_list);
        }
        jedis.close();
        List<Province> provinceList = JSON.parseArray(province_list, Province.class);
        return provinceList;
    }
}
