// 각 국가에 대한 장르 정보를 배열로 정의
var genresByNation = {
    '대한민국': ['순정만화', '소년만화', '성인만화', '기획도서', '만화잡지'],
    '일본': ['순정만화', '소년만화', '성인만화', '기획도서'],
    '미국': ['DC 코믹스마블', '코믹스리터러리', '그래픽노블'],
};
// 국가 select 요소의 change 이벤트 핸들러 등록
$('#nation-select').on('change', function() {
    var selectedNation = $(this).val();
    var genres = genresByNation[selectedNation] || [];

    console.log(genresByNation);
    // 기존의 option 요소 제거
    $('#genre-select').empty();

    // 새로운 option 요소 추가
    if (genres.length) {
        genres.forEach(function(genre) {
            $('#genre-select').append($('<option>', {
                value: genre, text: genre
            }));
        });
    } else {
        $('#genre-select').append($('<option>', {
            value: '', text: '해당 국가의 장르 정보가 없습니다'
        }));
    }
});
