$(".add-favourites").click(function() {
   $.ajax({
   type: 'POST',
   url: 'api/users/'
       + $(this).attr('data-id-user')
       + 'favourites',
       success: function () {
       alert("Książka została dodana do ulubionych")
           location.reload();
       }
   })
});

$(".remove-favourites").click(function () {
$.ajax({
    type: 'DELETE',
    url: 'api/users/'
        + $(this).attr('data-id-user')
        + 'favourites'
        + $(this).attr('data-id-book'),
    success: function () {
        alert("Książka została usunięta ulubionych")
        location.reload();
    }
})
});