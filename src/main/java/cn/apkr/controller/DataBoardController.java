package cn.apkr.controller;

import cn.apkr.service.ComplexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;


@Controller
public class DataBoardController {

	@Autowired
	private ComplexDataService complexDataService;

	@GetMapping("/dataBoard")
	public String showDataBoard(HttpServletRequest request, HttpSession session)  {

		// 登录判断
		boolean isLogined = false;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("loginState")) {
				if (cookie.getValue().equals("1")) {	// 判断是否处于登录状态
					isLogined = true;
				}
			}
		}
		if (!isLogined) return "redirect:/";

		// 为初次展示填充默认查询数据
		if (session.getAttribute("scoreList") == null)
			session.setAttribute("scoreList", complexDataService.selectAll());

		// 判断登录状态跳转页面
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("username")) {
				String rootList = (String) session.getAttribute("rootList");
				if (rootList != null) {
					boolean isRoot = rootList.contains(cookie.getValue());
					// 判断是否管理员来跳转至不同功能的页面
					return isRoot ? "/jsp/showRoot.jsp" : "/jsp/show.jsp";
				}
			}
		}
		return "redirect:/";
	}

	@GetMapping("/search")
	public String searchData(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String selectKeyword = request.getParameter("selectKeyword");

		// 刷新展示数据
		String[] keywords = selectKeyword.split("_");
		session.setAttribute("scoreList", complexDataService.search(keywords));
		session.setAttribute("keywords", selectKeyword);
		return "redirect:/dataBoard";
	}
}
