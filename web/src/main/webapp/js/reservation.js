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
