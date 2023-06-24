package cn.apkr.controller;


import cn.apkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (request.getParameter("signIn") != null) {
			// 登录
			if (userService.signIn(username, password)) {
				// 进入数据面板
				response.addCookie(new Cookie("loginState", "1"));
				response.addCookie(new Cookie("username", username));
				response.addCookie(new Cookie("password", password));
				return "redirect:/dataBoard";	// 唯一出口...
			} else {
				// 登录失败返回
				session.setAttribute("errorTips", "用户名或密码错误");
			}
		} else if (request.getParameter("signUp") != null) {
			// 注册
			if (username.length() < username.getBytes().length) {
				session.setAttribute("errorTips", "用户名格式有误)");
			} else if (userService.signUp(username, password)) {
				PrintWriter writer = response.getWriter();
				writer.write("注册成功! &nbsp");
				writer.write("<a href=\"./welcome\"><input type=\"button\" value=\"返回登录界面\"></a>");
			} else {
				session.setAttribute("errorTips", "用户名重复");
			}
		} else {
			// 注销
			System.out.println("注销用户");
			response.addCookie(new Cookie("loginState", "0"));
			response.addCookie(new Cookie("username", null));
			response.addCookie(new Cookie("password", null));
		}
		return "redirect:/";
	}
}
