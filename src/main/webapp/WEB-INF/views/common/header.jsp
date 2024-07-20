<%@ page import="com.example.demo.config.GeneralConfig" %>
<%@ page import="com.example.demo.util.LoginUtil" %><%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-20
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/main.css">

<div class="">
    <img style="max-height: 40px;" src="${contextPath}/static/img/logo/logo2.png">
</div>
<div class="align-right">
    <button class="default br-dark-blue bg-white" onclick="location.href='${contextPath}<%=GeneralConfig.SIGN_UP_URL%>'">Profile</button>
</div>