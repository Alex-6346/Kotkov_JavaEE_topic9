create table books
(
    isbn   varchar(30) primary key,
    title  varchar(40) not null,
    author varchar(40) not null
);

create table user_to_books (
                               user_id int not null,
                               book_isbn varchar(30) not null,
                               constraint fk_user_to_book_user foreign key (user_id) references users(id),
                               constraint fk_user_to_book_book foreign key (book_isbn) references books(isbn)
);