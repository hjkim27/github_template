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
        <div>
            <div>${item.title}</div>
            <div>
                <c:forEach var="labelId" items="${item.labelList}">
                    <c:set var="label" value="${labels[labelId]}"/>
                    <span name="label" id="label-${labelId}" data-color="${label.color}">${label.name}</span>
                </c:forEach>
            </div>
        </div>
    </div>
</c:forEach>
<script>
    // hex to rgb
    function hexToRgb(hex) {
        var arrBuff = new ArrayBuffer(4);
        var vw = new DataView(arrBuff);
        vw.setUint32(0, parseInt(hex, 16), false);
        var arrByte = new Uint8Array(arrBuff);
        var count = 0;
        for (let i = 1; i < 3; i++) {
            if (arrByte[i] > 125) {
                count++
            }
        }
        return count;
    }

    $('span[name="label"]').each(function () {
        let item = $(this);
        let dColor = item.data('color');
        item.css('background-color', dColor);
        var cc = hexToRgb(dColor);
        item.css('color', (cc < 2) ? '#ffffff' : '#000000')
    })


</script>