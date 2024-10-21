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
    <input type="hidden" id="desc" name="desc" value="${search.desc}">
    <%-- ----------- --%>
    <input type="text" id="searchValue" name="searchValue" class="min-size" value="${search.searchValue}"
           style="<c:if test="${!filterType}">grid-column:1/3</c:if>"
    >
    <%-- ----------- --%>
    <!-- Button trigger modal -->
    <%-- filterType 이 true 일 경우에만 filterType 검색 사용 --%>
    <c:if test="${filterType}">
        <button id="filterType-btn" type="button" class="btn bg-white-hover-blue br-dark-blue min-size"
                data-toggle="modal"
                data-target="#filterTypeModal">
            Type
        </button>

        <!-- Modal -->
        <div id="filterTypeModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="filterTypeModalLabel">
            <div class="modal-dialog filterType" role="document">
                <div id="filterTypeModalContent" class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Select Type</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <c:choose>
                            <c:when test="${path eq 'repositories'}">
                                <div name="filterType" class="modal-search-btn" data-value="">
                                    <div>All</div>
                                </div>
                                <div name="filterType" class="modal-search-btn" data-value="private">
                                    <div>private</div>
                                </div>
                                <div name="filterType" class="modal-search-btn" data-value="public">
                                    <div>public</div>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <%-- ----------- --%>
    <!-- Button trigger modal -->
    <button id="sortColumn-btn" type="button" class="btn bg-white-hover-blue br-dark-blue min-size" data-toggle="modal"
            data-target="#sortColumnModal">
        Sort
    </button>
    <!-- Modal -->
    <div id="sortColumnModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="sortColumnModalLabel">
        <div class="modal-dialog sortColumn" role="document">
            <div id="sortColumnModalContent" class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Select Order</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <c:choose>
                        <c:when test="${path eq 'labels'}">
                            <div name="sortColumn" class="modal-search-btn" data-value="1">
                                <i class="fas fa-check selectChecksortColumn"></i>
                                <div>Alphabetically</div>
                            </div>
                            <div name="sortColumn" class="modal-search-btn" data-value="1" data-desc="true">
                                <div>Reverse Alphabetically</div>
                            </div>
                            <div name="sortColumn" class="modal-search-btn" data-value="4" data-desc="true">
                                <div>Most Issues</div>
                            </div>
                            <div name="sortColumn" class="modal-search-btn" data-value="4">
                                <div>Fewest Issues</div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div name="sortColumn" class="modal-search-btn" data-value="1">
                                <i class="fas fa-check selectChecksortColumn"></i>
                                <div>name</div>
                            </div>
                            <div name="sortColumn" class="modal-search-btn" data-value="2" data-desc="true">
                                <div>Newest</div>
                            </div>
                            <div name="sortColumn" class="modal-search-btn" data-value="2">
                                <div>Oldest</div>
                            </div>
                            <div name="sortColumn" class="modal-search-btn" data-value="3">
                                <div>Last Updated</div>
                            </div>
                            <div name="sortColumn" class="modal-search-btn" data-value="3" data-desc="true">
                                <div>Recently Updated</div>
                            </div>
                        </c:otherwise>
                    </c:choose>
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
            sortColumn: $('#sortColumn').val() * 1,
            desc: $('#desc').val(),
            repositorySid: getStorage('repositorySid')
        }
        $.ajax({
            type: "post",
            url: '${contextPath}/ghRepository/ajax/${path}',
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

</script>
<script type="text/javascript" src="${contextPath}/static/js/default/search.js"></script>