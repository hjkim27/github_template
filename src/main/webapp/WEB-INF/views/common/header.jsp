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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/custom/main.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/static/fontawesome/5.15.4/css/all.min.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/bootstrap/bootstrap-3.4.1.min.css">
<script type="text/javascript" src="${contextPath}/static/js/jquery/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/bootstrap/bootstrap-3.4.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/default/common.js"></script>

<%-- [2024-09-15] menu 페이지 별도 분리 --%>
<div class="header">
    <div class="">
        <img style="max-height: 40px;"
        <%-- logo 클릭 시 home 으로 돌아가도록 추가 --%>
             onclick="location.href = '${contextPath}'"
             src="${contextPath}/static/img/logo/logo2-clean.png">
    </div>
    <div class="align-right">
        <button class="default br-dark-blue bg-white" onclick="location.href='${contextPath}/sign/logout'">Profile
        </button>
    </div>
</div>

<script>
    // 상세조회
    function openItem(obj) {
        var search = {
            sid: obj,
            repositorySid: getStorage('repositorySid')
        }
        $.ajax({
            type: "post",
            url: '${contextPath}/ghRepository/detail/${path}',
            data: search,
            success: function (result) {
                $('#ajax-container').html(result);
            },
            error: function (e) {
                console.log(e);
            }
        })
    }

    // 검색 함수 분리
    // 단일/다중 타입 검색에 대해 공통으로 사용할 수 있게 함
    function search() {
        let search = {
            searchValue: $('#searchValue').val(),
            filterType: $('#filterType').val(),
            sortColumn: $('#sortColumn').val() * 1,
            desc: $('#desc').val(),
            repositorySid: getStorage('repositorySid')
        }

        // 다중 검색을 위한 list 전달
        if (${multiType}) {
            search['filterTypeList'] = Array.from(filterTypeSet);
        }

        $.ajax({
            type: "post",
            url: '${contextPath}/ghRepository/ajax/${path}',
            data: search,
            success: function (result) {
                $('#ajax-container').html(result);
                // type, sort 검색 후 modal 을 닫기위함
                $('.close').click();
            },
            error: function (e) {
                console.log(e);
            }
        })
    }
</script>