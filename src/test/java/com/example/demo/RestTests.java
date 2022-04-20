package com.example.demo;

import com.example.demo.controller.BookController;
import com.example.demo.model.BookDto;
import com.example.demo.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;

@SpringBootTest
class RestTests {

    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository bookRepository;


    @Test
    void addBookTest(){
        bookRepository.clear();
        BookDto book = new BookDto("book1","0001","jack jackson");
        ResponseEntity<BookDto> response = bookController.saveBook(book);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(book);
        Assertions.assertThat(bookRepository.getBooks().size()).isEqualTo(1);
        Assertions.assertThat(bookRepository.getBooks().get(0)).isEqualTo(book);
    }

    @Test
    void getBooksTest(){
        bookRepository.clear();
        BookDto book1 = new BookDto("book1","0001","jack jackson");
        BookDto book2 = new BookDto("book2","0002","john johnson");
        BookDto book3 = new BookDto("book3","0003","jill jillson");

        bookController.saveBook(book1);
        bookController.saveBook(book2);
        bookController.saveBook(book3);

        ArrayList<BookDto> allBooks = bookController.bookFormGet(null);
        Assertions.assertThat(allBooks.size()).isEqualTo(3);
        Assertions.assertThat(allBooks).containsExactly(book1,book2,book3);
    }

    @Test
    void searchBooksTest(){
        bookRepository.clear();
        BookDto book1 = new BookDto("book1","0001","jack jackson");
        BookDto book2 = new BookDto("book2","0002","john johnson");
        BookDto book3 = new BookDto("book2again","0003","jill jillson");

        bookController.saveBook(book1);
        bookController.saveBook(book2);
        bookController.saveBook(book3);

        ArrayList<BookDto> allBooks = bookController.bookFormGet("2");
        Assertions.assertThat(allBooks.size()).isEqualTo(2);
        Assertions.assertThat(allBooks).containsExactly(book2,book3);
    }

}