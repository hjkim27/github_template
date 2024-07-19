<%@ page import="com.example.demo.config.GeneralConfig" %><%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-08
  Time: 오후 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
  <title>hjkim27.Daily</title>
  <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/login.css">
  <script type="text/javascript" src="${contextPath}/static/js/jquery-3.7.1.min.js"></script>
</head>
<body class="bg-dark-blue">
<div class="container login">
  <div id="findForm" class="input-form">
    <div id="logo">
      <img src="${contextPath}/static/img/logo/logo-clean.png" style="filter: invert()">
    </div>
    <div>
      <input id="loginId" type="text" data-name="아이디" placeholder="아이디"/>
    </div>
    <div>
      <input id="name" type="text" data-name="이름" placeholder="이름"/>
    </div>
    <div>
      <input id="loginPw" type="password" data-name="비밀번호" placeholder="비밀번호"/>
    </div>
    <div>
      <input id="loginPwCheck" type="password" data-name="비밀번호확인" placeholder="비밀번호확인"/>
    </div>
    <div class="grid-column2">
      <button class="bg-blue" onclick="signUp()">등록</button>
      <button class="bg-white" onclick="location.href='${contextPath}<%=GeneralConfig.HOME_URL%>'">등록취소</button>
    </div>
  </div>
</div>
<script>
  function signUp() {
    var valid = validate();
    if (!valid) {
      return;
    }

    $.ajax({
      url: "${contextPath}<%=GeneralConfig.SIGN_UP_URL%>",
      type: "post",
      async: false,
      data: {
        loginId: $('#loginId').val(),
        loginPw: $('#loginPw').val(),
        loginPwCheck: $('#loginPwCheck').val()
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
