<%@ page import="com.github.hjkim27.config.GeneralConfig" %>
<%@ page import="com.github.hjkim27.util.login.LoginUtil" %><%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-09-15
  Time: 오전 12:25
  - menu페이지 별도 분리
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
    .menu-button {
        height: 40px;
        margin-top: 20px;
        border-radius: 5px 5px 0 0;
        text-align: left;
        padding: 0 20px;
    }
</style>
<div style="display: flex; border-bottom: 1px solid var(--gray-scale-7); flex-direction: column;">
    <button id="menu-home" class="bg-white-hover-blue border-none menu-button" onclick="showMenu('home')">home</button>
    <button id="menu-repositories" class="bg-white-hover-blue border-none menu-button" onclick="showMenu('repositories')">Repositories</button>
    <button id="menu-issues" class="bg-white-hover-blue border-none menu-button" onclick="showMenu('issues')">Issues</button>
    <button id="menu-labels" class="bg-white-hover-blue border-none menu-button" onclick="showMenu('labels')">Labels</button>
    <button id="menu-settings" class="bg-white-hover-blue border-none menu-button" onclick="showMenu('settings')">Settings</button>
</div>

<script>
    function showMenu(path){
        console.log('${contextPath}/projectRepository/'+path);
        location.href = '${contextPath}/projectRepository/' + path;
    }
</script>