package com.jian.service;

import com.jian.dao.LinkManDao;
import com.jian.entry.PageBean;
import com.jian.pojo.LinkMan;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jian
 * @date 2021/1/11 12:40
 */
public class LinkManService {
    private LinkManDao linkManDao = new LinkManDao();
    public List<LinkMan> findAll() throws SQLException {
        return linkManDao.findAll();
    }

    public void add(LinkMan linkMan) throws SQLException {
        linkManDao.add(linkMan);
    }

    public void delete(Integer id) throws SQLException {
        linkManDao.delete(id);
    }

    public LinkMan findOne(Integer id) throws SQLException {
        return linkManDao.findOne(id);
    }

    public void update(LinkMan linkMan) throws SQLException {
        linkManDao.update(linkMan);
    }

    public PageBean<LinkMan> findByPage(Long currentPage, Integer pageSize) throws SQLException {
        PageBean<LinkMan> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        Long totalSize = linkManDao.findTotalPage();
        pageBean.setTotalSize(totalSize);
        Long totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        pageBean.setTotalPage(totalPage);
        List<LinkMan> linkManList = linkManDao.findPageList(currentPage, pageSize);
        pageBean.setList(linkManList);
        return pageBean;
    }
}
