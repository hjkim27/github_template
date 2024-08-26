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
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/project.css">
<div id="loadTarget">
    <%-- ----------- --%>
    <input type="text" id="searchValue" name="searchValue" value="${search.searchValue}">
    <%-- ----------- --%>
    <select id="privacyType" name="privacyType" onchange="search()">
        <option value="-">All</option>
        <option value="private" <c:if test="${search.privacyType eq 'private'}">select</c:if>>privavte</option>
        <option value="pubilc" <c:if test="${search.privacyType eq 'public'}">select</c:if>>public</option>
    </select>
    <%-- ----------- --%>
    <select id="sortColumn" name="sortColumn" onchange="search()">
        <option value="-1">Sort</option>
        <option value="2" <c:if test="${search.sortColumn eq 2}">select</c:if>>name</option>
        <option value="8" <c:if test="${search.sortColumn eq 8}">select</c:if>>createdAt</option>
        <option value="9" <c:if test="${search.sortColumn eq 9}">select</c:if>>updatedAt</option>
    </select>
</div>
<%-- ajax 검색 결과 --%>
<div id="ajax-container">
    <jsp:include page="ajax/repositories.jsp"/>
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
            url: '${contextPath}/projectRepository/ajax/repositories',
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