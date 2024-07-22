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
</head>
<body class="bg-dark-blue">
<div class="container">
    <div class="header">
        <jsp:include page="common/noLoginHeader.jsp"/>
    </div>
</div>
</body>
</html>
