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
    $('#desc').val($(this).data('desc') !== undefined);
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