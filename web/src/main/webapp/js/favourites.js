$(".add-favourites").click(function () {
   $.ajax({
   type: 'PUT',
   url: '/api/favourites/users/'
       + $(this).attr('data-id-user')
       + '/books/'
       + $(this).attr('data-id-book'),
       success: function () {
       alert("Książka została dodana do ulubionych")
           location.reload();
       }
   })
});

$(".remove-favourites").click(function () {
$.ajax({
    type: 'DELETE',
    url: '/api/favourites/users/'
        + $(this).attr('data-id-user')
        + '/books/'
        + $(this).attr('data-id-book'),
    success: function () {
        alert("Książka została usunięta ulubionych")
        location.reload();
    }
})
});