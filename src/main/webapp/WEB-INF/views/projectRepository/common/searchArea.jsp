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
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/custom/project.css">
<div id="loadTarget">
    <%-- ----------- --%>
    <input type="hidden" id="filterType" name="filterType" value="${search.filterType}">
    <input type="hidden" id="sortColumn" name="sortColumn" value="${search.sortColumn}">
    <%-- ----------- --%>
    <input type="text" id="searchValue" name="searchValue" class="min-size" value="${search.searchValue}">
    <%-- ----------- --%>
    <!-- Button trigger modal -->
    <button id="filterType-btn" type="button" class="btn bg-white-hover-blue br-dark-blue min-size" data-toggle="modal"
            data-target="#filterTypeModal">
        Type
    </button>

    <!-- Modal -->
    <div id="filterTypeModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="filterTypeModalLabel">
        <div class="modal-dialog" role="document">
            <div id="filterTypeModalContent" class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Select Type</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <c:choose>
                    <c:when test="${path eq 'repositories'}">
                        <div class="modal-body">
                            <div name="filterType" class="modal-search-btn" data-value="">All</div>
                            <div name="filterType" class="modal-search-btn" data-value="private">private</div>
                            <div name="filterType" class="modal-search-btn" data-value="public">public</div>
                        </div>
                    </c:when>
                    <c:when test="${path eq 'issues'}">
                        <div class="modal-body">
                            <c:forEach var="item" items="${labelList}">
                                <div name="filterType" class="modal-search-btn grid-gap-10"
                                     style="grid-template-columns: 20px auto" data-value="${item.labelId}">
                                    <div style="width:12px; height: 12px; background-color: ${item.color}"></div>
                                    <div>${item.name}</div>
                                    <div style="grid-column: 2; font-size: 12px">${item.description}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
    <%-- ----------- --%>
    <!-- Button trigger modal -->
    <button id="sortColumn-btn" type="button" class="btn bg-white-hover-blue br-dark-blue min-size" data-toggle="modal"
            data-target="#sortColumnModal">
        Sort
    </button>
    <!-- Modal -->
    <div id="sortColumnModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="sortColumnModalLabel">
        <div class="modal-dialog" role="document">
            <div id="sortColumnModalContent" class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Select Order</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div name="sortColumn" class="modal-search-btn" data-value="2">name</div>
                    <div name="sortColumn" class="modal-search-btn" data-value="8">createdAt</div>
                    <div name="sortColumn" class="modal-search-btn" data-value="9">updatedAt</div>
                </div>
            </div>
        </div>
    </div>
    <%-- ----------- --%>
</div>
<script>
    $('#searchValue').on('keyup', (e) => {
        search();
    })

    function search() {
        var search = {
            searchValue: $('#searchValue').val(),
            filterType: $('#filterType').val(),
            sortColumn: $('#sortColumn').val() * 1
        }
        $.ajax({
            type: "post",
            url: '${contextPath}/projectRepository/ajax/${path}',
            data: search,
            success: function (result) {
                $('#ajax-container').html(result);
                // type, sort 검색 후 modal 을 닫기위함
                $('.close').click();
            },
            error: function (e) {
                console.log(e);
            }
        })
    }

    // UI 화면이 변경되었을 경우에도 modal 을 제위치에 출력하기 위해...
    setInterval(() => {
        setModalPosition('filterType');
        setModalPosition('sortColumn');
    })

    function setModalPosition(targetId) {
        var target = $('#' + targetId + '-btn');
        var height = target.height();
        var pos = target.position();
        var modalDiv = $('#' + targetId + 'ModalContent');
        modalDiv.css('left', pos.left);
        modalDiv.css('top', pos.top + target.height() + 15);
    }

    // 이전 검색으로 추가된 i tag 제거
    function removeCheck(id) {
        $('.selectCheck' + id).remove();
    }

    // type, sort 검색 >> modal 에서 버튼 클릭 시 동작
    $('.modal-search-btn').click(function () {
        var name = $(this).attr('name');
        $('#' + name).val($(this).data('value'));
        removeCheck(name)
        var htmlVar = $(this).html();
        htmlVar = '<i class="fas fa-check selectCheck' + name + '"></i>' + htmlVar;
        $(this).html(htmlVar);
        search();
    })

    // modal버튼에 hover 효과 추가
    $('.modal-search-btn').mouseover(function () {
        var div = $(this);
        div.css('background-color', 'var(--blue-scale-9)');
    })

    // modal버튼에서 마우스를 치웠을 경우
    $('.modal-search-btn').mouseout(function () {
        var div = $(this);
        div.css('background-color', 'var(--gray-scale-9)');
    })

</script>