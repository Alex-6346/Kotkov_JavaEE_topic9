(function(){

    $('#saveBookForm').submit(function(e){
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: '/book/save',
            dataType: 'json',
            data: JSON.stringify({
                isbn: $(this).find('[name=isbn]').val(),
                title: $(this).find('[name=title]').val(),
                author: $(this).find('[name=author]').val()
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Content-Type', 'application/json')
            },
            success: function (response) {
                if(response.message == "OK"){
                    alert("ok");
                    updateBooksTable();

                } else {
                    alert("Some problem happened");
                }
            }
        });
    });

    function updateBooksTable() {
        $.ajax({
            url: '/book/all',
            success: function (response) {

                fillBooksTable(response);
            }
        });
    }

    function fillBooksTable(books) {
        let tableContent = "";
        for (let i = 0; i < books.length; ++i) {
            tableContent += `
        <tr th:each="book: ${books}">
            <td>${books[i].title}</td>
            <td>${books[i].isbn}</td>
            <td>${books[i].author}</td>
        </tr>
        `;
        }

        $("#tableBody").html(tableContent);
    }

})();