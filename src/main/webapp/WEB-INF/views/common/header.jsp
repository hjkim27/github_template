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

<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/main.css">
<script type="text/javascript" src="${contextPath}/static/js/jquery-3.7.1.min.js"></script>

<style>
    .menu-button {
        width: 100px;
        height: 40px;
        margin-top: 20px;
        border-radius: 5px 5px 0 0;
    }
</style>
<div class="">
    <img style="max-height: 40px;" src="${contextPath}/static/img/logo/logo2-clean.png">
</div>
<div class="align-right">
    <button class="default br-dark-blue bg-white" onclick="location.href='${contextPath}/sign/logout'">Profile</button>
</div>
<div style="grid-column: 1/3; display: flex; border-bottom: 1px solid var(--gray-scale-7);">
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
        /*$.ajax({
            url: '${contextPath}/projectRepository/'+url,
            type: 'get',
            success: function (data){
                $('#menuArea').html(data);
            },
            error: function (e){
                console.log(e);
            }
        })*/
    }
</script>