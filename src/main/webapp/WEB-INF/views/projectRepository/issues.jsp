<%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-22
  Time: 오후 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/custom/project.css">
<div id="loadTarget">
    <%-- ----------- --%>
    <input type="text" id="searchValue" name="searchValue" value="">
    <%-- ----------- --%>
    <select id="labelId" name="labelId" onchange="search()">
        <option value="-">All</option>
    </select>
    <%-- ----------- --%>
    <select id="sortColumn" name="sortColumn" onchange="search()">
        <option value="-1">Sort</option>
    </select>
</div>
<%-- ajax 검색 결과 --%>
<div id="ajax-container">
    <jsp:include page="ajax/issues.jsp"/>
</div>

<script>
    $('#searchValue').on('keyup', (e) => {
        search();
    })

    function search() {
        var search = {
            searchValue: $('#searchValue').val(),
            privacyType: $('#privacyType').val(),
            sortColumn: $('#sortColumn').val() * 1
        }
        $.ajax({
            type: "post",
            url: '${contextPath}/projectRepository/ajax/issues',
            data: search,
            success: function (result) {
                $('#ajax-container').html(result);
            },
            error: function (e) {
                console.log(e);
            }
        })
    }
</script>