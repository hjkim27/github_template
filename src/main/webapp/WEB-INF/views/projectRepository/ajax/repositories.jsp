<%--
  Created by IntelliJ IDEA.
  User: aa827
  Date: 2024-07-22
  Time: 오후 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="item" items="${list}">
    <div class="repo item">
        <div>
            <a class="repo name" href="javascript:window.open('${item.htmlUrl}')">${item.name}</a>
            <span class="repo privacyType">
              <c:if test="${item.privacy}">private</c:if>
              <c:if test="${!item.privacy}">public</c:if>
          </span>
        </div>
        <div class="repo descript">${item.description}</div>
    </div>
</c:forEach>