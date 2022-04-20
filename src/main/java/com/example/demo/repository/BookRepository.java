package com.example.demo.repository;

import com.example.demo.model.BookDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Locale;

@Repository
public class BookRepository {

    private ArrayList<BookDto> books = new ArrayList<>();

    public ArrayList<BookDto> getBooks(){
        return books;
    }

    public ArrayList<BookDto> searchBooks(String title){
        ArrayList<BookDto> searchBooks = new ArrayList<>();
        for (BookDto book:books) {
            if(book.getTitle().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT))) {
                searchBooks.add(book);
            }
        }
        return searchBooks;
    }

    public void addBook(BookDto bookDto){
        books.add(bookDto);
    }

    public void clear(){
        books.clear();
    }

}
