<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <title>登录界面</title>
</head>
<body>
    <div class="box-bg">
        <div id="box" class="box">
            <!-- 导入标题html -->
            <script src="${pageContext.request.contextPath}/js/htmlTitle.js"></script>
            <form action="${pageContext.request.contextPath}/login" method="post" class="login-bg">
                <div class="inputs-bg">
                    <div class="username-bg">
                        <label for="username">用户名:</label>
                        <input type="text" id="username" name="username" placeholder="请输入用户名" required>
                    </div>
                    <div class="password-bg">
                        <label for="password">密码:</label>
                        <input type="password" id="password" name="password" placeholder="请输入密码" required>
                    </div>
                </div>
                <div class="error-tips">
                    <%
                        String errorTips = (String) session.getAttribute("errorTips");
                        out.print(errorTips != null ? errorTips : " ");
                        session.setAttribute("errorTips", " ");
                    %>
                </div>
                <div class="btns-bg">
                    <input type="submit" name="signIn" value="登录">
                    <input type="submit" name="signUp" value="注册">
                </div>
            </form>
        </div>
    </div>
</body>
</html>