package com.example.demo.model;

import lombok.Data;

@Data
public class BookDto {

    private String title;
    private String isbn;
    private String author;

    public BookDto(String title, String isbn, String author) {
        this.title=title;
        this.isbn=isbn;
        this.author=author;
    }
}
