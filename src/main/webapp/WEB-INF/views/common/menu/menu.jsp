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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div id="step-main" style="display: flex; border-bottom: 1px solid var(--gray-scale-7); flex-direction: column;">
    <button id="menu-home" class="bg-white-hover-blue border-none menu-button"
            data-path="home" data-type="main">
        home
    </button>
    <button id="menu-repositories" class="bg-white-hover-blue border-none menu-button"
            data-path="repositories" data-type="main">
        Repositories
    </button>
    <button id="menu-issues" class="bg-white-hover-blue border-none menu-button"
            data-path="issues" data-type="sub">
        Issues
    </button>
    <button id="menu-labels" class="bg-white-hover-blue border-none menu-button"
            data-path="labels" data-type="sub">
        Labels
    </button>
    <button id="menu-settings" class="bg-white-hover-blue border-none menu-button"
            data-path="settings" data-type="sub">
        Settings
    </button>
</div>

<script>
    // [2024-09-16] path 에 따라 button을 보여주도록 수정
    switch ('${path}') {
        case 'home':
        case 'repositories':
            $('[data-type="main"]').show();
            $('[data-type="sub"]').hide();
            break;
        default:
            $('[data-type="main"]').hide();
            $('[data-type="sub"]').show();
            break;
    }

    // [2024-09-16] menu 클릭 함수 수정
    $('button').on('click', function () {
        var path = $(this).data('path');
        // [2024-09-23] data-type 이 sub 일 경우에만 repositorySid 를 넘기도록 추가
        var url = '${contextPath}/projectRepository/' + path;
        if($(this).data('type') === 'sub'){
            var repositorySid = getStorage('repositorySid');
            url += '?repositorySid=' + repositorySid;
        }
        location.href = url;
    })
</script>