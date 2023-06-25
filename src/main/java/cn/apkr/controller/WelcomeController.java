package cn.apkr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class WelcomeController {

	@Value("${rootList}")
	private String rootList;	// 自动注入管理员信息

	@GetMapping("/")
	public String showWelcomePage(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();

		// 导入管理员信息并存入session
		session.setAttribute("rootList", rootList);

		// 检查Cookie登录信息
		if (cookies != null) {	// 为空则表示从未访问过本网站或者禁止使用cookie
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginState")) {
					if (cookie.getValue().equals("1")) {
						return "/dataBoard";
					} else {
						break;
					}
				}
			}
		} else {
			// 请求初始化Cookie
			response.addCookie(new Cookie("loginState", "0"));
		}
		// 跳转至欢迎页（暂时没有欢迎页所以是当前项目的登录界面）
		return "./jsp/login.jsp";
	}
}