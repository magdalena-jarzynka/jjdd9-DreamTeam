$(".add-favourites").click(function () {
    let $a = jQuery(this);
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
    let $a = jQuery(this);
    $.ajax({
    type: 'DELETE',
    url: '/api/favourites/users/'
        + $(this).attr('data-id-user')
        + '/books/'
        + $(this).attr('data-id-book'),
    success: function () {
        alert("Książka została usunięta z ulubionych")
        location.reload();
    }
})
});