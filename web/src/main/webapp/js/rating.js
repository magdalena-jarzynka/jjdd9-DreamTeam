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


// $('.ratings_stars').bind('click', function () {
//     var star = this;
//     var widget = $(this).parent();
//
//     var clicked_data = {
//         clicked_on: $(star).attr('class'),
//         widget_id: $(star).parent().attr('id')
//     };
//     $.post(
//         'ratings.php',
//         clicked_data,
//         function (INFO) {
//             widget.data('fsr', INFO);
//             set_votes(widget);
//         },
//         'json'
//     );
// });
//
//
// function set_votes(widget) {
//
//     var avg = $(widget).data('fsr').whole_avg;
//     var votes = $(widget).data('fsr').number_votes;
//     var exact = $(widget).data('fsr').dec_avg;
//
//     window.console && console.log('and now in set_votes, it thinks the fsr is ' + $(widget).data('fsr').number_votes);
//
//     $(widget).find('.star_' + avg).prevAll().andSelf().addClass('ratings_vote');
//     $(widget).find('.star_' + avg).nextAll().removeClass('ratings_vote');
//     $(widget).find('.total_votes').text(votes + ' votes recorded (' + exact + ' rating)');
// }