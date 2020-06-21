$('.ratings_stars').hover(
    function () {
        $(this).prevAll().andSelf().addClass('ratings_over');
        $(this).nextAll().removeClass('ratings_vote');
    },
    function () {
        $(this).prevAll().andSelf().removeClass('ratings_over');
    }
);

$('.ratings_stars').click(function () {
    $(this).prevAll().andSelf().addClass('ratings_vote');
    $.ajax({
        type: 'POST',
        url: '/api/rating/books/'
            + $(this).attr('data-id-book')
            + '/rating/'
            + $(this).attr('data-id-rating'),
        success: function (result) {
            alert('Ocena została wystawiona.');
            location.reload();
        }
    });
});
