package com.jian.web.servlet;

import com.jian.entry.ResultBean;
import com.jian.pojo.Province;
import com.jian.service.ProvinceService;
import com.jian.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Jian
 * @date 2021/1/14 16:04
 */
@WebServlet("/province")
public class ProvinceServlet extends HttpServlet {
    private ProvinceService provinceService = new ProvinceService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Class aClass = this.getClass();
        try {
            Method method = aClass.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultBean resultBean = new ResultBean(true);
        try {
            List<Province> provinceList = provinceService.findAll();
            resultBean.setData(provinceList);
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("获取省份失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
}
