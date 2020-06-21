$(".change-access").click(function () {
    $.ajax({
        type: 'PATCH',
        url: '/api/users/'
            + $(this).attr('data-id-user'),

        success: function () {
            alert("Zmieniono poziom uprawnień użytkownika.");
            location.reload();
        }
    })
});