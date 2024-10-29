<%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-10-28
  Time: 오전 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-area">
    <c:set var="total" value="${search.totalSize}"/>
    <c:set var="min" value="${search.minPage}"/>
    <c:set var="max" value="${search.lastPage}"/>
    <c:set var="interval" value="${search.pageInterval}"/>
    <c:if test="${max - min + 1 > interval}">
        <c:set var="max" value="${min + interval -1}"/>
        <c:set var="next" value="${max+1}"/>
    </c:if>

    <c:if test="${min > interval}">
        <span class="page" onclick="search(1)">
            <i class="fas fa-angle-double-left"></i>
        </span>
        <span class="page" onclick="search(${min -interval})">
            <i class="fas fa-angle-left"></i>
        </span>
    </c:if>
    <c:forEach var="page" begin="${min}" end="${max}">
        <span class="page <c:if test="${page eq search.pageNum}">current-page</c:if>"
              onclick="search(${page})">${page}</span>
    </c:forEach>
    <c:if test="${next != null}">
        <span class="page" onclick="search(${next})">
            <i class="fas fa-angle-right"></i>
        </span>
        <span class="page" onclick="search(${search.lastPage})">
            <i class="fas fa-angle-double-right"></i>
        </span>
    </c:if>

</div>
<div>
    - ${search.pageNum} -
</div>
<style>
    .page {
        border: 1px solid var(--gray-scale-4);
        width: 30px;
        text-align: center;
        padding: 5px;
        border-radius: 2px;
    }

    .page-area {
        display: flex;
        justify-content: center;
    }

    .current-page {
        background-color: var(--blue-scale-7);
    }
</style>