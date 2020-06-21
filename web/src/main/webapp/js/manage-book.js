$(".remove-book").click(function () {
    if (confirm('Czy na pewno chcesz usunąć książkę z bazy danych?')) {
        $.ajax({
            type: 'DELETE',
            url: '/api/manage/' + $(this).attr('data-id-book'),
            success: function (result) {
                alert('Książka została usunięta z bazy danych');
                window.location.href = "/browse";
            }
        });
    }
});