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
        <div class="repo item">
            <div style="display: flex">
                <div style="margin-right: 10px;">#${item.issueNumber} ${item.title}</div>
                <div>
                    <c:forEach var="labelId" items="${item.labelIdList}">
                        <c:set var="label" value="${labels[labelId]}"/>
                        <%-- [2024-09-16] issue에는 label 이 있더라도 label 목록에 해당 값이 없을 경우 빈 span 출력 방지 --%>
                        <c:if test="${label ne null}">
                            <span class="repo round-box" name="label" id="label-${labelId}" data-color="${label.color}">${label.name}</span>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div style="font-size: 12px; color: var(--gray-scale-8)">
                <c:if test="${item.createdAt ne null}">
                    CreatedAt : <fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd hh:mm"/>
                </c:if>
            </div>
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