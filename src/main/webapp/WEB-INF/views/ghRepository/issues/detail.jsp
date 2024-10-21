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
<%-- 검색 결과가 없을 경우 --%>
<c:if test="${item eq null}">
    !
</c:if>
<c:if test="${item ne null}">
        <div class="repo item grid-gap-10" style="margin-bottom: 10px; margin: 0" >
            ${item}
            <div class="repo ">${item.title} #${item.issueNumber}</div>
                <c:if test="${item.pullRequest}">
                    [Merged]
                </c:if>
                <c:if test="${!item.pullRequest}">
                    [${item.state}]
                    <c:if test="${item.createdAt ne null}">
                        CreatedAt : <fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd hh:mm"/>
                    </c:if>
                </c:if>
            <div class="repo ">${item.body}</div>
        </div>
</c:if>

<button type="button" class="btn bg-white-hover-blue br-dark-blue min-size" onclick="search()">toList</button>
