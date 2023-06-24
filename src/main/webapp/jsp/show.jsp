<%@ page import="java.util.Objects" %>
<%@ page import="cn.apkr.domain.ComplexData" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/showRoot.css">
    <script src="${pageContext.request.contextPath}/js/showRoot.js"></script>
    <title>管理员面板</title>
</head>
<body>
<div class="box-bg">
    <div id="box" class="box">
        <div class="topping-bar">
            <a href="${pageContext.request.contextPath}/login" class="logout">注销</a>
        </div>
        <!--导入标题-->
        <script src="${pageContext.request.contextPath}/js/htmlTitle.js"></script>
        <%--搜索栏--%>
        <form action="${pageContext.request.contextPath}/search" class="search-bg">
            <div class="inputs-bg">
                <input type="text" name="selectKeyword" placeholder="输入关键字搜索" value="<%
                            String keywords = (String) session.getAttribute("keywords");
                            out.write(Objects.requireNonNullElse(keywords, ""));
                        %>"/>
                <input type="submit" name="selectData" value="搜索"/>
                <div class="error-tips">
                    <%= Objects.requireNonNullElse((String) session.getAttribute("errorTips"), "") %>
                </div>
                <div class="help-tips"></div>
            </div>
        </form>
        <div class="display-bg">
            <table id="data-table" class="data-table" border="1px" cellspacing="0px">
                <tr>
                    <th class="sid-tag">学号</th>
                    <th class="classname-tag">班级</th>
                    <th class="name-tag">姓名</th>
                    <th class="cid-tag">科目</th>
                    <th class="score-tag">成绩</th>
                </tr>
                <%
                    List<ComplexData> scoreList = (List<ComplexData>) session.getAttribute("scoreList");
                    for (ComplexData complexData : scoreList) {
                        out.write("<tr>");
                        out.write("<td class=\"sid-tag\">" + complexData.getSid() + "</td>");
                        out.write("<td class=\"classname-tag\">" + complexData.getClassname() + "</td>");
                        out.write("<td class=\"name-tag\">" + complexData.getName() + "</td>");
                        out.write("<td class=\"courseName-tag\">" + complexData.getCourseName() + "</td>");
                        out.write("<td class=\"score-tag\">" + complexData.getScore() + "</td>");
                        out.write("</tr>");
                    }
                %>
            </table>
        </div>
    </div>
</div>
</body>
</html>