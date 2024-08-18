<%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-08
  Time: 오후 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>hjkim27.github</title>
</head>
<body>
<div class="container">
<div class="header">
    <jsp:include page="../common/header.jsp"/>
</div>
<div id="container-body" class="wrapper grid-gap-10 grid-column-1-3">
    <div class="" style="border: 1px solid #5b676d; height: 100vh;">
        <jsp:include page="../common/profile.jsp"/>
    </div>

    <div id="menuArea" class="" style="border: 1px solid #5b676d;  height: 100%;">
        <c:choose>
            <c:when test="${path eq 'home'}"><jsp:include page="home.jsp"/></c:when>
            <c:when test="${path eq 'repositories'}"><jsp:include page="repositories.jsp"/></c:when>
            <c:when test="${path eq 'labels'}"><jsp:include page="labels.jsp"/></c:when>
            <c:when test="${path eq 'settings'}"><jsp:include page="settings.jsp"/></c:when>
        </c:choose>
    </div>
</div>
</div>
</body>
</html>
