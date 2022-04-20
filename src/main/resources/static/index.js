(function(){

    $('#saveBookForm').submit(function(e){
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: '/book/save',
            dataType: 'json',
            data: JSON.stringify({
                title: $(this).find('[name=title]').val(),
                isbn: $(this).find('[name=isbn]').val(),
                author: $(this).find('[name=author]').val()
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Content-Type', 'application/json')
            },
            success: function (response) {
                    console.log(response);
                    updateBooksTable();
                }
        });
    });

    function updateBooksTable() {
        $.ajax({
            url: '/book/search',
            success: function (response) {
                fillBooksTable(response);
            }
        });
    }


})();


(function(){
    $('#searchBookForm').submit(function(e){
        e.preventDefault();

        $.ajax({
            type: 'GET',
            url: '/book/search',
            dataType: 'json',
            data: {
                title: $("#searchTitle").val()
            },
                beforeSend: function(xhr) {
                xhr.setRequestHeader('Content-Type', 'application/json')
            },
            success: function (response) {
                console.log(response);
                fillBooksTable(response);
            }
        });
    });
})();


function fillBooksTable(books) {
    let content = "";
    for (let i = 0; i < books.length; ++i) {
        content += `
        <tr th:each="book: ${books}">
            <td>${books[i].title}</td>
            <td>${books[i].isbn}</td>
            <td>${books[i].author}</td>
        </tr>
        `;
    }
    $("#tableBody").html(content);
}