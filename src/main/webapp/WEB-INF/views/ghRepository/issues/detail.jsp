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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 검색 결과가 없을 경우 --%>
<c:if test="${item eq null}">
    !
</c:if>
<c:if test="${item ne null}">
    <div class="repo item grid-gap-10" style="margin-bottom: 10px; margin: 0">
        <div class="repo ">${item.title} #${item.issueNumber}</div>
            ${item.type}
        <c:if test="${item.createdAt ne null}">
            CreatedAt : <fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd hh:mm"/>
        </c:if>
        <div class="repo">${item.body}</div>
        <c:if test="${list ne null}">
            <div class=" item-group">
                <c:forEach var="mapItem" items="${list}">
                    <c:set var="obj" value="${mapItem.value}"/>
                    <c:if test="${obj.eventType}">
                        <div style="height: 30px; margin-top: 10px; color: var(--point-color-light)">
                            <i class="fas fa-seedling"></i>
                        </div>
                        <div>
                            <b>${obj.ghActorLogin}</b> ${obj.createdAt}
                        </div>
                        <div class="vertical-line"></div>
                        <div style="min-height: 30px">
                            <div>${obj.title}</div>
                            <div style="padding-left: 10px">${obj.body}</div>
                            <div>${fn:substring(obj.commitId,0 ,6 )}</div>
                        </div>
                    </c:if>
                    <c:if test="${!obj.eventType}">
                        ${obj}
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
    </div>
</c:if>

<button type="button" class="btn bg-white-hover-blue br-dark-blue min-size" onclick="search()">toList</button>