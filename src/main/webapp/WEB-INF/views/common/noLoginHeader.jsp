<%@ page import="com.github.hjkim27.config.GeneralConfig" %>
<%@ page import="com.github.hjkim27.util.login.LoginUtil" %><%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-20
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/custom/main.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/bootstrap/bootstrap-3.4.1.min.css">
<script type="text/javascript" src="${contextPath}/static/js/jquery/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/bootstrap/bootstrap-3.4.1.min.js"></script>

<div class="">
    <img style="max-height: 40px; filter: invert()" src="${contextPath}/static/img/logo/logo2-clean.png">
</div>
<div class="align-right">
    <button class="default sign border-none" onclick="location.href='${contextPath}<%=GeneralConfig.SIGN_IN_URL%>'">Sign in</button>
    <button class="default sign border-bright" onclick="location.href='${contextPath}<%=GeneralConfig.SIGN_UP_URL%>'">Sign up</button>
</div>