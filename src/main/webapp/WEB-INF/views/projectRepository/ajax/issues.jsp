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

<c:forEach var="item" items="${list}">
    <div class="repo item grid-column-2-1 grid-gap-10">
            ${item}
        <div>
            <div>${item.title}</div>
            <div>
                <c:forEach var="label" items="${item.labelLIdList}">
                    <span>${label}</span>
                </c:forEach>
            </div>
        </div>
    </div>
</c:forEach>