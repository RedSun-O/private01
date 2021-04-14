package com.jian.web;

import com.jian.entry.PageBean;
import com.jian.entry.ResultBean;
import com.jian.pojo.LinkMan;
import com.jian.service.LinkManService;
import com.jian.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jian
 * @date 2021/1/11 12:39
 */
@WebServlet("/linkman")
public class ServletLinkman extends HttpServlet {
    private LinkManService linkManService = new LinkManService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            Class<? extends ServletLinkman> aClass = this.getClass();
            Method method = aClass.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            List<LinkMan> linkManList = linkManService.findAll();
            resultBean.setData(linkManList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("查询失败");
        }
        JsonUtils.printResult(response, resultBean);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            LinkMan linkMan = JsonUtils.parseJSON2Object(request, LinkMan.class);
            linkManService.add(linkMan);
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("添加失败");
        }
        JsonUtils.printResult(response,resultBean);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        ResultBean resultBean = new ResultBean(true);
        try {
            linkManService.delete(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }
        JsonUtils.printResult(response,resultBean);
    }

    private void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
            ResultBean resultBean = new ResultBean(true);
            Integer id = Integer.valueOf(request.getParameter("id"));
        try {
            LinkMan linkMan = linkManService.findOne(id);
            resultBean.setData(linkMan);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

            resultBean.setFlag(false);
            resultBean.setErrorMsg("获取联系人失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            LinkMan linkMan = JsonUtils.parseJSON2Object(request, LinkMan.class);
            linkManService.update(linkMan);
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("修改失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    private void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        Long currentPage = Long.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        try {
            PageBean<LinkMan> pageBean = linkManService.findByPage(currentPage,pageSize);
            resultBean.setData(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("分页查询失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
}
