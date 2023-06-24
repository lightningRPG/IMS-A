package cn.apkr.controller;

import cn.apkr.service.ComplexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
//@RequestMapping("data")
public class ComplexDataController {

	@Autowired
	private ComplexDataService complexDataService;

	@RequestMapping("/update")
	public String updateData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		request.setCharacterEncoding("utf-8");
		String updateSid = request.getParameter("updateSid");
		String updateCourseName = request.getParameter("updateCourseName");
		Float updateScore = Float.valueOf(request.getParameter("updateScore"));

		// 登录控制
		boolean isLogined = false;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("loginState")) {
				if (cookie.getValue().equals("1")) {	// 判断是否处于登录状态
					isLogined = true;
				}
			}
		}
		if (!isLogined) return "redirect:/";


		if (updateSid != null && updateCourseName != null) {
			Boolean isSuccess = complexDataService.modifyBySidCourseName(updateSid, updateCourseName, updateScore);
			if (isSuccess) {
				session.setAttribute("scoreList", complexDataService.selectAll());
				response.getWriter().write("success");
			} else {
				response.getWriter().write("fail");
			}
		}
		return "redirect:/";
	}

	@RequestMapping("/delete")
	public String deleteData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		String deleteSid = request.getParameter("deleteSid");
		String deleteCourseName = request.getParameter("deleteCourseName");

		// 登录控制
		boolean isLogined = false;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("loginState")) {
				if (cookie.getValue().equals("1")) {	// 判断是否处于登录状态
					isLogined = true;
				}
			}
		}
		if (!isLogined) return "redirect:/";

		if (deleteSid != null && deleteCourseName != null) {
			Boolean isSuccess = complexDataService.modifyBySidCourseName(deleteSid, deleteCourseName, null);
			if (isSuccess) {
				session.setAttribute("scoreList", complexDataService.selectAll());
				response.getWriter().write("success");
			} else {
				response.getWriter().write("fail");
			}
		}

		return "redirect:/";
	}

}
