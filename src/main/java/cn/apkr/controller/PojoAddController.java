package cn.apkr.controller;

import cn.apkr.domain.Course;
import cn.apkr.domain.Score;
import cn.apkr.domain.Student;
import cn.apkr.service.CourseService;
import cn.apkr.service.ScoreService;
import cn.apkr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PojoAddController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ScoreService scoreService;

	@RequestMapping("/dataAdd")
	public String showAddPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		request.setCharacterEncoding("utf-8");

		return "./jsp/add.jsp";
	}

	@RequestMapping("/insert")
	public String addPojo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		request.setCharacterEncoding("utf-8");
		request.setAttribute("insertError", null);
		String submitBtn = request.getParameter("submitBtn");
		Boolean isSuccess = false;

		if (submitBtn.contains("学生")) {
			String sid = request.getParameter("sid");
			String name = request.getParameter("name");
			String classname = request.getParameter("classname");
			String sex = request.getParameter("sex");

			// 此处可以包装至Service层
			// 判断重复
			Student testStd = new Student();
			testStd.setSid(sid);
			boolean isStdEmpty = studentService.selectStudent(testStd).isEmpty();
			if (isStdEmpty) {
				Student tempStd = new Student(sid, name, classname, sex);
				isSuccess = studentService.insertStudent(tempStd);
			}

			if (!isSuccess) {
				session.setAttribute("insertError", "学生信息添加失败");
			}
		}
		else if (submitBtn.contains("课程")) {
			String cid = request.getParameter("cid");
			String teacherName = request.getParameter("teacherName");
			String courseName = request.getParameter("courseName");

			// 此处可以包装至Service层
			Course testCor = new Course();
			testCor.setCid(cid);
			boolean isCorEmpty = courseService.selectCourse(testCor).isEmpty();
			if (isCorEmpty) {
				Course tempCor = new Course(cid, teacherName, courseName);
				isSuccess = courseService.insertCourse(tempCor);
			}

			if (!isSuccess) {
				session.setAttribute("insertError", "课程信息添加失败");
			}
		}
		else if (submitBtn.contains("分数")) {
			String sid = request.getParameter("sid");
			String cid = request.getParameter("cid");
			Float score = Float.valueOf(request.getParameter("score"));

			// 此处可以包装至Service层
			// 判断是否存在分数（是则修改不是则插入）
			Score tempSco = new Score();
			tempSco.setSid(sid);
			tempSco.setCid(cid);

			if (scoreService.selectScore(tempSco).isEmpty()) {
				// 插入分数
				Student testStd = new Student();
				Course testCor = new Course();
				testStd.setSid(sid);
				testCor.setCid(cid);
				boolean isStdEmpty = studentService.selectStudent(testStd).isEmpty();
				boolean isCorEmpty = courseService.selectCourse(testCor).isEmpty();
				System.out.println(isStdEmpty);
				System.out.println(isCorEmpty);
				if (!isStdEmpty && !isCorEmpty) {		// 确认学生和课程都存在
					tempSco = new Score(sid, cid, score);
					isSuccess = scoreService.insertScore(tempSco);
				}
			} else {
				isSuccess = scoreService.updateScoreById(tempSco);
			}

			if (!isSuccess) {
				session.setAttribute("insertError", "分数录入失败");
			}
		}

		return "redirect:/dataAdd";
	}
}
