function doSearch(search) {
    if (search.length < 3) {
        console.log(search);
        $("#search").autocomplete({source: []});
            return;
    }
    $.ajax({
        url: '/search',
        type: 'GET',
        data: {
            'search': search,
        },
        success: function (data) {
            $("#search").autocomplete({
                source: $.map(JSON.parse(data), function (book) {
                    let text = book.title + " - ";
                    for (let key in book.authorViews) {
                        if (!book.authorViews.hasOwnProperty(key)) continue;
                        text += book.authorViews[key].name + " ";
                    }
                    return {value: text, id: book.id};
                }),
                select: function (e, ui) {
                    window.location.href = "/single?id=" + ui.item.id;
                },
            });
        }
    });
}