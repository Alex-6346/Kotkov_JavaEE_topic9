package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


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
    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|"+
    "(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|"+
            "(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?"+
            "[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message="Incorrect format of ISBN") //there was no way i could make this regex myself :)
    private String isbn;


    @Column(name = "title")
    @NotBlank(message = "Title cannot be blank")
    private String title;


    @Column(name = "author")
    @NotBlank(message = "Author cannot be blank")
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
