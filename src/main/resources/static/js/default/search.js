/**
 * <pre>
 *     type, sort 검색 시 사용하는 modal 에 대한 script를 분리한 js 파일
 *     단일r선택, 다중선택에 대해 추가되어있음.
 * </pre>
 *
 * @author hjkim
 * @since 2024-09-11
 */

/*단일 선택 모달*/
// type, sort 검색 >> modal 에서 버튼 클릭 시 동작
$('.modal-search-btn').click(function () {
    var name = $(this).attr('name');
    $('#' + name).val($(this).data('value'));
    $('#desc').val($(this).data('desc') !== undefined);
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


/*다중 선택 모달*/
// type, sort 검색 >> modal 에서 버튼 클릭 시 동작
$('.modal-search-btn-multi').click(function () {
    var dValue = $(this).data('value');

    if (filterTypeSet.has(dValue)) {
        filterTypeSet.delete(dValue);
        removeCheck(dValue);
    } else {
        filterTypeSet.add(dValue);
        var htmlVar = $(this).html();
        htmlVar = '<i class="fas fa-check selectCheck' + dValue + '"></i>' + htmlVar;
        $(this).html(htmlVar);
    }
    // label에서 type 검색 시 정렬이 변경되는 에러가 있었음.
    // $('#desc').val($(this).data('desc') !== undefined);
    search();
})

// modal버튼에 hover 효과 추가
$('.modal-search-btn-multi').mouseover(function () {
    var div = $(this);
    div.css('background-color', 'var(--blue-scale-9)');
})

// modal버튼에서 마우스를 치웠을 경우
$('.modal-search-btn-multi').mouseout(function () {
    var div = $(this);
    div.css('background-color', 'var(--gray-scale-9)');
})

// issue state 검색 추가
// state 검색 시 searchValue 값에 해당 state 의 value 가 입력되도록 함
$('div[name="issue-state"]').click(function () {
    let val = $('#searchValue').val();
    let stateVal = $(this).data('value');
    if (val.indexOf(stateVal) > -1) {
        val = val.replace(' ' + stateVal, '');
        $(this).css('color', 'var(--gray-scale-8)');
    } else {
        val += ' ' + stateVal;
        $(this).css('color', 'var(--gray-scale-0)');
    }
    $('#searchValue').val(val);
    search();
})