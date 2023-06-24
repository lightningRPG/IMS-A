<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add.css">
    <script src="${pageContext.request.contextPath}/js/add.js"></script>
    <title>添加数据(临时)</title>
    <style>
        form {
            width: 500px;
            margin: 20px auto;
            text-align: center;
        }

        .inputs-bg {
            display: inline-block;
            text-align: left;
        }

        input[type="submit"] {
            height: 30px;
            margin: 10px;
            padding: 0 10px;
        }

        .error-tips {
            margin-top: 30px;
            text-align: center;
            font-size: 12px;
            color: red;
        }
    </style>
</head>
<body>
    <div class="box-bg">
        <div id="box" class="box">
            <script src="${pageContext.request.contextPath}/js/htmlTitle.js"></script>
            <!-- TODO:添加学生、添加课程、录入分数 -->
            <!-- 正在考虑要不要整合到showRoot的搜索界面与标题之间的位置 -->
            <!-- 点击+号弹出，帅！三个按钮切换三种插入界面，帅！ -->

            <div class="error-tips">
                <%
                    String insertError = (String) session.getAttribute("insertError");
                    if (Objects.nonNull(insertError)) {
                        out.write(insertError);
                        session.setAttribute("insertError", "");
                    }
                %>
            </div>
            <form action="${pageContext.request.contextPath}/insert" method="post" class="addStudent">
                <h3 class="title">添加学生信息</h3>
                <div class="inputs-bg">
                    学号: <input type="text" name="sid" placeholder="输入学生编号" required/><br>
                    姓名: <input type="text" name="name" placeholder="输入学生姓名" required/><br>
                    班级: <input type="text" name="classname" placeholder="输入班级名称" required><br>
                    性别:
                    <input type="radio" name="sex" value="男" checked> 男
                    <input type="radio" name="sex" value="女"> 女
                </div>
                <br><input type="submit" name="submitBtn" value="添加学生信息">
            </form>
            <form action="${pageContext.request.contextPath}/insert" method="post" class="addCourse">
                <h3 class="title">添加课程信息</h3>
                <div class="inputs-bg">
                    课号: <input type="text" name="cid" placeholder="输入课程编号" required><br>
                    课名: <input type="text" name="courseName" placeholder="输入课程名字" required><br>
                    名字: <input type="text" name="teacherName" placeholder="输入老师名字" required><br>
                </div>
                <br><input type="submit" name="submitBtn" value="添加课程信息">
            </form>
            <form action="${pageContext.request.contextPath}/insert" method="post" class="addScore">
                <h3 class="title">录入学生成绩</h3>
                <div class="inputs-bg">
                    学号: <input type="text" name="sid" placeholder="输入学生编号" required><br>
                    课号: <input type="text" name="cid" placeholder="输入课程编号" required><br>
                    分数: <input type="text" name="score" placeholder="输入分数" required>
                </div>
                <br><input type="submit" name="submitBtn" value="录入分数">
            </form>
        </div>
    </div>
</body>
</html>