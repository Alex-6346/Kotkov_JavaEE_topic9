package com.example.demo;

import com.example.demo.entity.BookDto;
import com.example.demo.service.BookService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@MySpringTestListener
@SpringBootTest
@DatabaseTearDown("/BookService/clean-up.xml")
class BookServiceTests {

    @Autowired
    private BookService bookService;


    @Test
    @ExpectedDatabase(value = "/BookService/afterAddBookTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void addBookTest_initdb() {
        bookService.createBook("book1","title harry","nothing");
    }

    @Test
    @DatabaseSetup("/BookService/init.xml")
    void selectAllBooks_initdb(){
        Assertions.assertThat(bookService.getBooks()).hasSize(3).
                containsExactlyInAnyOrder(new BookDto("book1","title harry","nothing"),
                        new BookDto("book2","title harry potter","jkr"),
                        new BookDto("book3","nothing","jkr"));
    }

    @Test
    @DatabaseSetup("/BookService/init.xml")
    void selectBookById_initdb(){
        Assertions.assertThat(bookService.getBookByIsbn("book1")).isEqualTo(new BookDto("book1","title harry","nothing"));
    }

    @Test
    @DatabaseSetup("/BookService/init.xml")
    void selectBooksByTitle_initdb(){
        Assertions.assertThat(bookService.getBooksByTitle("har")).hasSize(2).
                containsExactlyInAnyOrder(new BookDto("book1","title harry","nothing"),
                        new BookDto("book2","title harry potter","jkr"));
    }

    @Test
    @DatabaseSetup("/BookService/init.xml")
    void selectBooksByAuthor_initdb(){
        Assertions.assertThat(bookService.getBooksByAuthor("jk")).hasSize(2).
                containsExactlyInAnyOrder(new BookDto("book2","title harry potter","jkr"),
                        new BookDto("book3","nothing","jkr"));
    }



}