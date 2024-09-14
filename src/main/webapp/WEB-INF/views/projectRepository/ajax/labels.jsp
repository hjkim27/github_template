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
        <div class="repo item grid-gap-10" style="margin-bottom: 10px; grid-template-columns: 180px auto; margin: 0" >
            <span class="repo round-box" style="width: fit-content; height: fit-content" name="label" id="label-${item.labelId}" data-color="${item.color}">${item.name}</span>
            <div class="repo descript" style="grid-column: 2/4">${item.description}</div>
        </div>
    </c:forEach>
</c:if>
<script>
    $('span[name="label"]').each(function () {
        let item = $(this);
        let dColor = item.data('color');
        item.css('background-color', dColor);
        item.css('border-color', dColor);
        var cc = hexToRgb(dColor);
        item.css('color', (cc < 2) ? '#ffffff' : '#000000')
    })
</script>