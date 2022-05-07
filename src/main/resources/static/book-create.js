//SAVE A BOOK
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

//SEARCH BOOKS
(function(){
    $('#searchBookByIsbnForm').submit(function(e){
        e.preventDefault();

        $.ajax({
            type: 'GET',
            url: '/book/search',
            dataType: 'json',
            data: {
                isbn: $("#searchIsbn").val(),
                query:"isbn"
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Content-Type', 'application/json')
            },
            success: function (response) {
                if(response==0){
                    alert("Book not found!");
                }
                else {
                    console.log(response);
                    fillBooksTable(response);
                    window.location.assign("/book/" + $("#searchIsbn").val());
                }
            }
        });
    });
})();

(function(){
    $('#searchBookByTitleForm').submit(function(e){
        e.preventDefault();

        $.ajax({
            type: 'GET',
            url: '/book/search',
            dataType: 'json',
            data: {
                title: $("#searchTitle").val(),
                query:"title"
            },
                beforeSend: function(xhr) {
                xhr.setRequestHeader('Content-Type', 'application/json')
            },
            success: function (response) {
                if(response==0){
                    alert("Books not found!");
                }
                else {
                    console.log(response);
                    fillBooksTable(response);
                }
            }
        });
    });
})();

(function(){
    $('#searchBookByAuthorForm').submit(function(e){
        e.preventDefault();

        $.ajax({
            type: 'GET',
            url: '/book/search',
            dataType: 'json',
            data: {
                author: $("#searchAuthor").val(),
                query:"author"
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Content-Type', 'application/json')
            },
            success: function (response) {
                if (response == 0) {
                    alert("Book not found!");
                } else {
                    console.log(response);
                    fillBooksTable(response);
                }
            }
        });
    });
})();


window.onload = addTolistOfFavouriteBooks();
function addTolistOfFavouriteBooks() {
//ADD BOOKS TO THE LIST OF FAVORITE
    let table = document.getElementById('listOfBooksTableBody');
    let rows = table.getElementsByTagName('tr');

    for (const row of rows) {
        const isbn = row.cells[0];
        const title = row.cells[1];
        const author = row.cells[2];
        row.cells[3].addEventListener('click', function (e) {
            e.preventDefault();
            $.ajax({
                type: 'POST',
                url: '/book/favourite-add',
                dataType: 'json',
                data: JSON.stringify({
                    title: $(title).text(),
                    isbn: $(isbn).text(),
                    author: $(author).text()
                }),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('Content-Type', 'application/json')
                },
                success: function (response) {
                        console.log(response);
                        alert("Book added to favourite!");

                },
                error: function(res){
                    alert("Book is already added to favourite!");
                }
            })
        })
    }
}



function fillBooksTable(books) {
    let content = "";
    for (let i = 0; i < books.length; ++i) {
        content += `
        <tr th:each="book: ${books}">
            <td>${books[i].isbn}</td>
            <td>${books[i].title}</td>
            <td>${books[i].author}</td>
            <td><button type="submit">Add as Favorite</button></td>
        </tr>
        `;
    }
    $("#listOfBooksTableBody").html(content);
    addTolistOfFavouriteBooks();
}