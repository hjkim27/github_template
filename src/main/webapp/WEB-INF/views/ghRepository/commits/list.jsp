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
    <c:forEach var="itemGroup" items="${list}">
        <div style="height: 30px; margin-top: 10px; color: var(--point-color-light)">
            <i class="fas fa-seedling"></i>
        </div>
        <div style="height: 30px; margin-top: 10px; font-size: 17px">
            Commits on ${itemGroup.key}
        </div>
        <div class="vertical-line"></div>
        <div class="commit-block">
            <c:forEach var="item" items="${itemGroup.value}" varStatus="status">
                <div class="repo item grid-gap-10"
                     style="margin-bottom: 10px; margin: 0; grid-column: 2">
                    <div name="commit-title" data-id="${item.sid}" class="repo">
                            ${item.title}
                        <c:if test="${item.body ne ''}">
                            <i class="far fa-comment-dots"></i>
                        </c:if>
                    </div>

                    <c:if test="${item.body ne ''}">
                        <div div id="commit-body-${item.sid}" style="display: none" data-show="false">
                                ${item.body}
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:forEach>
</c:if>
<div style="grid-column: 1/3">
    <jsp:include page="../common/pagination.jsp"/>
</div>

<script>
    // commit title 클릭 시 body 를 show/hide 처리
    $('div[name="commit-title"]').click(function () {
        let dataId = $(this).data('id');
        let target = $('#commit-body-' + dataId);
        let isShow = target.data('show');
        if (isShow) {
            target.hide();
        } else {
            target.show();
        }
        target.data('show', !isShow);
    })
</script>