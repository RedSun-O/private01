package com.jian.dao;

import com.jian.pojo.Province;
import com.jian.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jian
 * @date 2021/1/14 16:26
 */
public class ProvinceDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
    public List<Province> findAll() throws Exception {
        String sql = "select * from province";
        List<Province> provinceList = queryRunner.query(sql, new BeanListHandler<>(Province.class));
        return provinceList;
    }
}
