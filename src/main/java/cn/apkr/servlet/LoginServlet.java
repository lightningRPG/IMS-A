package cn.apkr.servlet;

import cn.apkr.config.SpringConfig;
import cn.apkr.service.UserService;
import cn.apkr.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("gbk");
        resp.setContentType("text/html");
        userService = (UserService) SpringConfig.ctx.getBean("userService");
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (req.getParameter("signIn") != null) {
            // 登录
            if (userService.signIn(username, password)) {
                // 进入数据面板
                resp.addCookie(new Cookie("loginState", "1"));
                resp.addCookie(new Cookie("username", username));
                resp.addCookie(new Cookie("password", password));
                resp.sendRedirect( req.getContextPath() + "/dataBoard");
            } else {
                // 登录失败返回
                session.setAttribute("errorTips", "用户名或密码错误");
                resp.sendRedirect(req.getContextPath() + "/welcome");
            }
        } else if (req.getParameter("signUp") != null) {
            // 注册
            if (username.length() < username.getBytes().length) {
                session.setAttribute("errorTips", "用户名格式有误)");
                resp.sendRedirect(req.getContextPath() + "/welcome");
            } else if (userService.signUp(username, password)) {
                PrintWriter writer = resp.getWriter();
                writer.write("注册成功! &nbsp");
                writer.write("<a href=\"./welcome\"><input type=\"button\" value=\"返回登录界面\"></a>");
            } else {
                session.setAttribute("errorTips", "用户名重复");
                resp.sendRedirect(req.getContextPath() + "/welcome");
            }
        } else {
            // 注销
            System.out.println("注销用户");
            resp.addCookie(new Cookie("loginState", "0"));
            resp.addCookie(new Cookie("username", null));
            resp.addCookie(new Cookie("password", null));
            resp.sendRedirect(req.getContextPath() + "/welcome");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
