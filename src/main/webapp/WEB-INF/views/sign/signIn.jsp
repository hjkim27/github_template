<%@ page import="com.github.hjkim27.config.GeneralConfig" %><%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-07
  Time: 오전 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>hjkim27.github</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/sign.css">
    <script type="text/javascript" src="${contextPath}/static/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="container login">
    <div id="loginForm" class="input-form">
        <div id="logo">
            <img src="${contextPath}/static/img/logo/logo.png">
        </div>
        <div>
            <input id="loginId" type="text" data-name="아이디" placeholder="아이디"/>
        </div>
        <div>
            <input id="loginPw" type="password" data-name="비밀번호" placeholder="비밀번호"/>
        </div>
        <div>
            <button class="bg-blue" onclick="login()">로그인</button>
        </div>
    </div>
    <div class="align-right text-link">
        <a href="${contextPath}/sign/register">계정등록</a>
    </div>
</div>
<script>
    if ('${loginSession}' !== '') {
        alert('로그인이 필요합니다');
    }

    function login() {
        var valid = validate();
        if (!valid) {
            return;
        }

        $.ajax({
            url: "${contextPath}<%=GeneralConfig.SIGN_IN_URL%>",
            type: "post",
            async: false,
            data: {
                loginId: $('#loginId').val(),
                loginPw: $('#loginPw').val()
            },
            success: function (data) {
                if (data.login) {
                    location.href = data.url;
                } else {
                    alert(JSON.stringify(data.message));
                }
            },
            error: function (e) {
                console.log(e)
            }
        })
    }

    function validate() {
        var check = true;
        $('#loginForm input').each(function () {
            if ($(this).val().trim().length === 0 && check) {
                alert('\'' + $(this).data('name') + '\' 입력이 필요합니다.')
                check = false;
                return;
            }
        })
        return check;
    }
</script>
</body>
</html>
