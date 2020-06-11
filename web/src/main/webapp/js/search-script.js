$(document).ready(function () {
    $('.js-example-basic-single').select2({
        ajax: {
            url: '/search',
            data: function (params) {
                var query = {
                    search: params.term
                };
                return query;
            },
            processResults: function (data) {
                console.log(data);
                return {
                    results: $.map(JSON.parse(data), function (book) {
                        let text = book.title + " - ";
                        for (let key in book.authorViews) {
                            if (!book.authorViews.hasOwnProperty(key)) continue;
                            console.log(book.authorViews[key]);
                            text += book.authorViews[key].name + " ";
                        }
                        return {id: book.id, text: text}
                    })
                };
            }
        },
        minimumInputLength: 3,
        placeholder: 'Wyszukaj',
        language: 'pl'
    });

    $('.js-example-basic-single').on('select2:select', function (e) {
        window.location.href = "/single?id=" + e.params.data.id;
    });
});

