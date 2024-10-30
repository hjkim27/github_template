<%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-08
  Time: 오후 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>hjkim27.github</title>
</head>
<body>
<div class="full-div">
    <!-- header Start -->
    <jsp:include page="../../common/header.jsp"/>
    <!-- header End -->

    <div class="grid-gap-10" style="grid-template-columns: 150px auto 150px;">
        <!-- menu Start  -->
        <jsp:include page="../../common/menu/menu.jsp"/>
        <!-- menu End -->

        <!-- container Start -->
        <div id="container-body" class="wrapper">
            <div id="menuArea" class="list-area">
                <jsp:include page="../common/searchArea.jsp"/>
                <%-- ajax 검색 결과 --%>
                <div id="ajax-container">
                    <jsp:include page="./list.jsp"/>
                </div>
            </div>
        </div>
        <!-- container End -->
    </div>
</div>
<script>
    /* header 선택에 따른 css 추가 */
    $('#menu-' + '${path}').css('border-bottom', '3px solid var(--point-color-light)');
</script>
</body>
</html>
