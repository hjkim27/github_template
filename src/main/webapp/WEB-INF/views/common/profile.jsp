<%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-20
  Time: 오후 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
    .profile-img-div {
        height: var(--size-300);
        width: var(--size-300);
        border-radius: 50%;
        border: 1px solid #3d4963;
        overflow: hidden;
        display: flex;
        justify-content: center;
    }

    .profile-img {
        max-height: var(--size-300);
    }

    .profile {
        margin: 50px;
        display: grid;
        justify-content: center
    }

    .profile > button {
        height: 50px;
        font-size: 20px;
        margin-top: 20px;
    }
</style>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/main.css">

<div class="profile" style="">
    <div class="profile-img-div" style="">
        <img class="profile-img" src="${contextPath}/static/img/logo/logo2.png">
    </div>
    <button class="bg-blue" style="">profile</button>
</div>
