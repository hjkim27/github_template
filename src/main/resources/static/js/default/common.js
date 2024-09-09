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

// filter, sort 등 modal 을 사용한 select 위치 지정
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
