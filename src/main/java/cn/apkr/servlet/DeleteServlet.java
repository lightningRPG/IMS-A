package cn.apkr.servlet;

import cn.apkr.config.MyBatisConfig;
import cn.apkr.config.SpringConfig;
import cn.apkr.service.ComplexDataService;
import cn.apkr.service.impl.ComplexDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

//@WebServlet(urlPatterns = "/delete", asyncSupported = true)   // 升级为SSM
public class DeleteServlet extends HttpServlet {
    private ComplexDataService complexDataService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        complexDataService = (ComplexDataService) SpringConfig.ctx.getBean("complexDataService");
        HttpSession session = req.getSession();
        String deleteSid = req.getParameter("deleteSid");
        String deleteCourseName = req.getParameter("deleteCourseName");

        // 登录控制
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("loginState")) {
                if (!cookie.getValue().equals("1")) {
                    resp.sendRedirect(req.getContextPath() + "/welcome");
                    return;
                }
            }
        }

        if (Objects.nonNull(deleteSid) && Objects.nonNull(deleteCourseName)) {
            Boolean isSuccess = complexDataService.modifyBySidCourseName(deleteSid, deleteCourseName, null);
            if (isSuccess) {
                session.setAttribute("scoreList", complexDataService.selectAll());
                resp.getWriter().write("success");
            } else {
                resp.getWriter().write("fail");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
