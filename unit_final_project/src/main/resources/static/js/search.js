$(document).ready(function () {
    $('#bookNamed').autocomplete({
            source: function (request, response) {
                $.get("http://localhost:8080/books/suggestions?", {q: request.term}, function (data, status) {
                    $("#results").html("");
                    if (status === 'success') {
                        response(data);
                    }
                });
            }
        }
    );

    $("#btnBookSearch").click(function () {
        const inputText = $("#bookNamed").val();
        if (inputText.length === 0) {
            alert("Enter product name or description");
        } else {
            let bookSearch = document.getElementById('bookSearch');
            if (bookSearch) {
                let input = document.createElement("input");
                input.setAttribute("type", "hidden");
                input.setAttribute("name", "bookSearch");
                input.setAttribute("value", inputText);
                bookSearch.appendChild(input);
            }
        }
    });
});