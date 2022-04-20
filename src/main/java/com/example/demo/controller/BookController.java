package com.example.demo.controller;

import com.example.demo.model.BookDto;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository = new BookRepository();


    @GetMapping
    public String bookPage(Model model){
        model.addAttribute("books", bookRepository.getBooks());
        return "book-create";
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<BookDto> saveBook(@RequestBody  BookDto bookDto){
        bookRepository.addBook(bookDto);
        return  ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }


    @GetMapping("/search")
    @ResponseBody
    public ArrayList<BookDto> bookFormGet(@RequestParam(required = false) String title){
        if(title!=null){
           return bookRepository.searchBooks(title);
        }
        return bookRepository.getBooks();
    }


}
