var pageSize = 20;

$(function () {
    toPage(1);
});


function toPage(num) {
    let audio =  $("#isAudio").val();
    let genre = $("#genre").val();
    audio = (audio === undefined) ? "blank" : audio;
    genre = (genre === undefined) ? "blank" : genre;

    $.ajax({
        url: '/browse',
        type: 'POST',
        data: {
            'pageNum': num,
            'pageSize': pageSize,
            'audio': audio,
            'genre': genre
        },
        success: function (data) {
            $(".container-fluid").empty();
            $(".container-fluid").append(data);
            $("#isAudio").val(audio);
            $("#genre").val(genre);
        }
    })
}