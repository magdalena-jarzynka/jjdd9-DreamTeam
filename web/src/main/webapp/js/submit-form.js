$("#add-form").submit(function (e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.
    var form = $(this);
    var url = form.attr('action');
    var title = $("#title").val();

    $.ajax({
        type: "POST",
        url: url,
        data: form.serialize(), // serializes the form's elements.
        success: function (data) {
            alert('Książka o tytule "' + title + '" została zapisana w bazie danych'); // show response from the php script.
            // location.reload();
        }
    });
});