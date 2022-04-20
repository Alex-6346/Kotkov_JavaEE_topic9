package com.example.demo.controller;

import com.example.demo.model.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@Controller
@RequestMapping("/book")
public class BookController {

    ArrayList<BookDto> books = new ArrayList<BookDto>();

    @RequestMapping(value ="/save", method=RequestMethod.POST)
    @ResponseBody
    public String saveBook(@RequestBody  BookDto bookDto){
        books.add(bookDto);
        System.out.println("added!");
        return "{\"message\": \"OK\"}";
    }


    @GetMapping
    public String bookPage(Model model){
        model.addAttribute("books", books);
        return "book-create";
    }

    @ResponseBody
    @GetMapping("/all")
    public ArrayList<BookDto> bookFormGet(@RequestParam(required = false) String title){
        if(title!=null){
            ArrayList<BookDto> searchBooks = new ArrayList<BookDto>();
            for (BookDto book:books) {
                if(book.getTitle().contains(title)) {
                    searchBooks.add(book);
                }
            }
            return searchBooks;
        }
        return books;
    }





}
