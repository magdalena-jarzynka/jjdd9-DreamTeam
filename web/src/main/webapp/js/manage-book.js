$(".remove-book").click(function () {
    if (confirm('Czy na pewno chcesz usunąć?')) {
        $.ajax({
            type: 'DELETE',
            url: '/api/manage/' + $(this).attr('data-id-book'),
            success: function (result) {
                alert('Książka usunięta');
                window.location.href = "/browse";
            }
        });
    }
});