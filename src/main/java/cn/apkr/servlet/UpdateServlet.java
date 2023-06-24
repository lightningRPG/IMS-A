package cn.apkr.servlet;

import cn.apkr.config.MyBatisConfig;
import cn.apkr.config.SpringConfig;
import cn.apkr.mapper.ComplexDataMapper;
import cn.apkr.service.ComplexDataService;
import cn.apkr.service.impl.ComplexDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {
    private ComplexDataService complexDataService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        complexDataService = (ComplexDataService) SpringConfig.ctx.getBean("complexDataService");
        HttpSession session = req.getSession();
        String updateSid = req.getParameter("updateSid");
        String updateCourseName = req.getParameter("updateCourseName");
        Float updateScore = Float.valueOf(req.getParameter("updateScore"));


        // 登录控制
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("loginState")) {
                if (!cookie.getValue().equals("1")) {
                    resp.sendRedirect(req.getContextPath() + "/welcome");
                    return;
                }
            }
        }

        if (Objects.nonNull(updateSid) && Objects.nonNull(updateCourseName)) {
            Boolean isSuccess = complexDataService.modifyBySidCourseName(updateSid, updateCourseName, updateScore);
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
