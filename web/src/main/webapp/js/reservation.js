$(".add-reservation").click(function () {
    $.ajax({
        type: 'POST',
        url: '/api/reservations/add-reservation/'
            + $(this).attr('data-id-user')
            + '/book/'
            + $(this).attr('data-id-book'),
        success: function () {
            alert("Na twój adres e-mail został wysłany link" +
                " - postępuj zgodnie z instrukcją aby potwierdzić rezerwację.");
            location.reload();
        }
    })
});

// $("deleteReservation").click(function (book) {
//     var postId = $(book.target).attr("data-id");
//     $.ajax({
//         url: "/api/reservations",
//         method: "DELETE",
//         data: {id: postId},
//         success: function () {
//             location.reload();
//         }
//     })
// });
