package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {
    @Id
    @Basic(optional = false)
    @Column(name = "isbn",unique=true)
    private String isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;

    public BookDto( String isbn, String title, String author) {
        this.isbn=isbn;
        this.title=title;
        this.author=author;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BookDto)) {
            return false;
        }
        BookDto book = (BookDto) o;
        return book.isbn.equals(this.isbn) && book.title.equals(this.title) && book.author.equals(this.author);
    }


}
