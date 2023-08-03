package cn.apkr.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WelcomePage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        Properties pro = new Properties();

        // 导入管理员信息并存入session
        pro.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/rootAccountData.properties")));
        session.setAttribute("rootList", pro.getProperty("rootList"));

        // 检查Cookie登录信息
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginState")) {
                if (cookie.getValue().equals("1")) {
                    resp.sendRedirect( req.getContextPath() + "/dataBoard");
                    return;
                } else {
                    break;
                }
            }
        }
        // 跳转至欢迎页（即当前项目的登录界面）
        req.getRequestDispatcher("/login").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
