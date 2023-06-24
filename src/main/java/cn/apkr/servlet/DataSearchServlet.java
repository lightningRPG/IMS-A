package cn.apkr.servlet;

import cn.apkr.config.SpringConfig;
import cn.apkr.service.ComplexDataService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DataSearchServlet extends HttpServlet {
    private ComplexDataService complexDataService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        String selectKeyword = req.getParameter("selectKeyword");
        complexDataService = (ComplexDataService) SpringConfig.ctx.getBean("complexDataService");

        // 刷新展示数据
        String[] keywords = selectKeyword.split("_");
        session.setAttribute("scoreList", complexDataService.search(keywords));
        session.setAttribute("keywords", selectKeyword);
        resp.sendRedirect(req.getContextPath() + "/dataBoard");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
