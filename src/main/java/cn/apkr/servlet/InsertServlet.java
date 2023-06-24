package cn.apkr.servlet;


import cn.apkr.config.MyBatisConfig;
import cn.apkr.config.SpringConfig;
import cn.apkr.domain.Course;
import cn.apkr.domain.Score;
import cn.apkr.domain.Student;
import cn.apkr.mapper.StudentMapper;
import cn.apkr.service.CourseService;
import cn.apkr.service.ScoreService;
import cn.apkr.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/insert")
public class InsertServlet extends HttpServlet {
    private StudentService studentService;
    private CourseService courseService;
    private ScoreService scoreService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        req.setAttribute("insertError", null);
        studentService = (StudentService) SpringConfig.ctx.getBean("studentService");
        courseService = (CourseService) SpringConfig.ctx.getBean("courseService");
        scoreService = (ScoreService) SpringConfig.ctx.getBean("scoreService");
        HttpSession session = req.getSession();
        String submitBtn = req.getParameter("submitBtn");
        Boolean isSuccess = false;

        if (submitBtn.contains("学生")) {
            String sid = req.getParameter("sid");
            String name = req.getParameter("name");
            String classname = req.getParameter("classname");
            String sex = req.getParameter("sex");

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
            String cid = req.getParameter("cid");
            String teacherName = req.getParameter("teacherName");
            String courseName = req.getParameter("courseName");

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
            String sid = req.getParameter("sid");
            String cid = req.getParameter("cid");
            Float score = Float.valueOf(req.getParameter("score"));

            // 此处可以包装至Service层
            // 判断是否存在分数（是则修改不是则插入）
            Score tempSco = new Score();
            tempSco.setSid(sid);
            tempSco.setCid(cid);
            boolean isScoEmpty = scoreService.selectScore(tempSco).isEmpty();

            if (isScoEmpty) {
                // 插入分数
                Student testStd = new Student();
                Course testCor = new Course();
                testStd.setSid(sid);
                testCor.setCid(cid);
                boolean isStdEmpty = studentService.selectStudent(testStd).isEmpty();
                boolean isCorEmpty = courseService.selectCourse(testCor).isEmpty();
                if (isStdEmpty && isCorEmpty) {
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

        resp.sendRedirect(req.getContextPath() + "/dataAdd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
