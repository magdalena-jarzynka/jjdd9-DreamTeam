var pageSize = 20;

$(function () {
    toPage(1);
});

function toPage(num) {
    let audio = $("#isAudio").val();
    let genre = $("#genre").val();
    let search = $("#search").val();
    audio = (audio === undefined) ? "blank" : audio;
    genre = (genre === undefined) ? "blank" : genre;

    $.ajax({
        url: '/browse',
        type: 'POST',
        data: {
            'pageNum': num,
            'pageSize': pageSize,
            'audio': audio,
            'genre': genre,
            'search': search,

        },
        success: function (data) {
            $(".container-fluid").empty();
            $(".container-fluid").append(data);
            $("#isAudio").val(audio);
            $("#genre").val(genre);
        }
    })
}

function toPageWithNoFilters(num) {
    let search = $("#search").val();
    if (search.length > 0 && search.length < 3) {
        return;
    }
    $.ajax({
        url: '/browse',
        type: 'POST',
        data: {
            'pageNum': num,
            'pageSize': pageSize,
            'search': search,
        },
        success: function (data) {
            $(".container-fluid").empty();
            $(".container-fluid").append(data);
            $("#search").autocomplete("close");
        }
    })
}