package cn.apkr.controller;

import cn.apkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("loginEx")
public class LoginExController {
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session,
			HttpServletResponse response
	) throws IOException {
		if (userService.signIn(username, password)) {
			// 登录成功，设置Cookie和Session信息
			Cookie loginStateCookie = new Cookie("loginState", "1");
			Cookie usernameCookie = new Cookie("username", username);
			Cookie passwordCookie = new Cookie("password", password);
			// 设置Cookie的有效期等信息
			// ...

			response.addCookie(loginStateCookie);
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);

			return "redirect:/dataBoard";
		} else {
			// 登录失败，设置错误提示信息
			session.setAttribute("errorTips", "用户名或密码错误");
			return "redirect:/welcome";
		}
	}

	@PostMapping("/register")
	public String register(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session,
			HttpServletResponse response
	) throws IOException {
		if (username.length() < username.getBytes().length) {
			session.setAttribute("errorTips", "用户名格式有误");
			return "redirect:/welcome";
		} else if (userService.signUp(username, password)) {
			// 注册成功
			return "registerSuccess";  // 返回注册成功页面
		} else {
			session.setAttribute("errorTips", "用户名重复");
			return "redirect:/welcome";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		// 注销用户，清除Cookie
		Cookie loginStateCookie = new Cookie("loginState", "0");
		Cookie usernameCookie = new Cookie("username", null);
		Cookie passwordCookie = new Cookie("password", null);
		// 设置Cookie的有效期等信息
		// ...

		response.addCookie(loginStateCookie);
		response.addCookie(usernameCookie);
		response.addCookie(passwordCookie);

		return "redirect:/welcome";
	}
}
