<%@ page import="com.example.demo.config.GeneralConfig" %><%--
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
    <script type="text/javascript" src="${contextPath}/static/js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/main.css">
</head>
<body class="bg-dark-blue">
<div class="container">
    <div id="header">
        <div class="">
            <img style="max-height: 20px;" src="${contextPath}/static/img/logo/logo-clean3.png">
        </div>
        <div class="align-right">
            <button class="default sign border-none" onclick="location.href='${contextPath}<%=GeneralConfig.SIGN_IN_URL%>'">Sign in</button>
            <button class="default sign border-bright" onclick="location.href='${contextPath}<%=GeneralConfig.SIGN_UP_URL%>'">Sign up</button>
        </div>
    </div>
</div>
</body>
</html>
