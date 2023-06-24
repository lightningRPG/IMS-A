package cn.apkr.servlet;

import cn.apkr.config.MyBatisConfig;
import cn.apkr.config.SpringConfig;
import cn.apkr.service.ComplexDataService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

// 目前的主要作用是根据权限展示不同页面
public class DataBoardServlet extends HttpServlet {
    private ComplexDataService complexDataService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        complexDataService = (ComplexDataService) SpringConfig.ctx.getBean("complexDataService");
        HttpSession session = req.getSession();
        String rootList = (String) session.getAttribute("rootList");

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("loginState")) {
                if (!cookie.getValue().equals("1")) {
                    resp.sendRedirect(req.getContextPath() + "/welcome");
                    return;
                }
            }
        }

        // 为初次展示填充默认查询数据
        if (session.getAttribute("scoreList") == null)
            session.setAttribute("scoreList", complexDataService.selectAll());

        // 判断登录状态跳转页面
        Boolean isRoot = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("username"))
                isRoot = rootList.contains(cookie.getValue());
        }
        if (isRoot != null) {
            String nextPage = isRoot ? "/jsp/showRoot.jsp" : "/jsp/show.jsp";
            req.getRequestDispatcher(nextPage).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/welcome");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
