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
    // [2024-09-19] modal 외 영역 클릭 시 modal 이 닫히는 동작 오류 수정
    // 기존 modal-dialog 내 content 만 위치를 지정하여 modal-dialog 는 최상단에 위치, 해당 영역 클릭 시 modal 이 닫히지 않는 문제가 있었음.
    var dialog = $('.modal-dialog.' + targetId);
    dialog.css('left', pos.left);
    dialog.css('top', pos.top + target.height() + 15);
}

// 이전 검색으로 추가된 i tag 제거
function removeCheck(id) {
    $('.selectCheck' + id).remove();
}

/* [2024-09-16] sessionStorage */
function setStorage(key, item) {
    sessionStorage.setItem(key, item);
}

function getStorage(key) {
    var item = sessionStorage.getItem(key);
    return (item == undefined || item === 'undefined') ? null : item;
}

function removeStorage(key) {
    sessionStorage.removeItem(key);
}