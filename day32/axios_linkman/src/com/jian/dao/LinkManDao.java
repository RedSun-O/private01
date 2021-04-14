package com.jian.dao;

import com.jian.pojo.LinkMan;
import com.jian.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jian
 * @date 2021/1/11 12:40
 */
public class LinkManDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
    public List<LinkMan> findAll() throws SQLException {
        String sql = "select * from linkman";
        List<LinkMan> linkManList = queryRunner.query(sql, new BeanListHandler<>(LinkMan.class));
        return linkManList;
    }

    public void add(LinkMan linkMan) throws SQLException {
        String sql = "insert into linkman values (null,?,?,?,?,?,?)";
        queryRunner.update(sql, linkMan.getName(), linkMan.getSex(), linkMan.getAge(), linkMan.getAddress(), linkMan.getQq(), linkMan.getEmail());
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from linkman where id=?";
        queryRunner.update(sql, id);
    }

    public LinkMan findOne(Integer id) throws SQLException {
        String sql = "select * from linkman where id=?";
        LinkMan linkMan = queryRunner.query(sql, new BeanHandler<>(LinkMan.class), id);
        return linkMan;
    }

    public void update(LinkMan linkMan) throws SQLException {
        String sql = "update linkman set name=?,sex=?,age=?,address=?,qq=?,email=? where id=?";
        queryRunner.update(sql, linkMan.getName(), linkMan.getSex(), linkMan.getAge(), linkMan.getAddress(), linkMan.getQq(), linkMan.getEmail(), linkMan.getId());
    }

    public Long findTotalPage() throws SQLException {
        String sql = "select count(*) from linkman";
        Long totalSize = (Long) queryRunner.query(sql, new ScalarHandler());
        return totalSize;
    }

    public List<LinkMan> findPageList(Long currentPage, Integer pageSize) throws SQLException {
        String sql = "select * from linkman limit ?,?";
        List<LinkMan> linkManList = queryRunner.query(sql, new BeanListHandler<>(LinkMan.class), (currentPage - 1) * pageSize, pageSize);
        return linkManList;
    }
}
