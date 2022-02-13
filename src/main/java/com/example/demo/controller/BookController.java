package com.example.demo.controller;

import com.example.demo.model.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping("/book")
public class BookController {

    ArrayList<BookDto> books = new ArrayList<BookDto>();

    @GetMapping("/create")
    public String bookFormGet(Model model){
        BookDto bookDto= new BookDto();
        model.addAttribute("bookDto",bookDto);
        return "book-create";
    }

    @PostMapping("/create")
    public String saveBook(BookDto bookDto,Model model){
        books.add(bookDto);
        model.addAttribute("bookDto",bookDto);
        model.addAttribute("books", books);

        return "book-list";
    }


}
