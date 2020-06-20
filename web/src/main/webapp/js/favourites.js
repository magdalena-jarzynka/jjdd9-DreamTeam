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
           // $(this.parent()).html("Usuń z ulubionych")
           $a.text("Usuń z ulubionych");
           $a.removeClass('btn templatemo-edit-btn add-favourites');
           $a.addClass('btn templatemo-edit-btn remove-favourites');
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
        // $(this.parent()).html("Dodaj do ulubionych")
        $a.text("Dodaj do ulubionych");
        $a.removeClass('btn templatemo-edit-btn remove-favourites');
        $a.addClass('btn templatemo-edit-btn add-favourites');
    }
})
});