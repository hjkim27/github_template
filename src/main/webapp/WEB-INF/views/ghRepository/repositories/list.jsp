<%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-22
  Time: 오후 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%-- 검색 결과가 없을 경우 --%>
<c:if test="${list eq null}">
    <div id="clear-filter" class="repo item" style="text-align: right; padding: 15px 0;"
         onclick="location.reload()">
        <i class="far fa-times-circle"> Clear Filter</i>
    </div>
    <div class="no-match-search">
        No results matched your search
    </div>
</c:if>
<c:if test="${list ne null}">
    <c:forEach var="item" items="${list}">
        <div class="repo item grid-column-2-1 grid-gap-10">
            <div>
                <%-- [2024-09-16] github url을 여는게 아니라 issue 목록 페이지로 이동하게 수정 --%>
                <a class="repo name" href="javascript:openRepo('${item.sid}')">${item.name}</a>
                <span class="repo round-box">
              <c:if test="${item.ghPrivate}">private</c:if>
              <c:if test="${!item.ghPrivate}">public</c:if>
          </span>
            </div>
            <div class="repo at-div"><fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd hh:mm"/></div>
            <div class="repo descript">${item.description}</div>
        </div>
    </c:forEach>
</c:if>

<script>
    // [2024-09-16] repository 에 해당하는 issue 만 보여지도록 하는 함수
    function openRepo(sid){
        setStorage('repositorySid', sid);
        location.href = '${contextPath}/ghRepository/commits?repositorySid='+sid;
    }
</script>