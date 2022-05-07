window.onload = deleteFromlistOfFavouriteBooks();
function deleteFromlistOfFavouriteBooks() {
//DELETE  BOOKS TO THE LIST OF FAVOURITE
    let table = document.getElementById('listOfFavouriteBooksTableBody');
    let rows = Array.from(table.getElementsByTagName('tr'));

    for (const [i, row] of rows.entries()) {

        const isbn = row.cells[0];
        const title = row.cells[1];
        const author = row.cells[2];
        row.cells[3].addEventListener('click', function (e) {
            e.preventDefault();

            $.ajax({
                type: 'POST',
                url: '/book/favourite-delete',
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
                    row.remove();
                    alert("Book deleted from favourite!");
                },
                error: function(res){
                    alert("Book is already deleted from favourite!");
                }
            })
        })
    }
}


