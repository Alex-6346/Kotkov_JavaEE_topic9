package com.example.demo.controller;

import com.example.demo.entity.BookDto;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public String bookPage(Model model){
        model.addAttribute("books", bookService.getBooks());
        return "book-create";
    }


    @GetMapping("/search")
    @ResponseBody
    public List<BookDto> bookFormGet(@RequestParam(required = false) String isbn,
                                     @RequestParam(required = false) String title,
                                     @RequestParam(required = false) String author,
                                     @RequestParam(required = false) String query){
        if(query!=null){
            if(query.equals("isbn")){
                return Collections.singletonList(bookService.getBookByIsbn(isbn));
            }
            if(query.equals("title")){
                return bookService.getBooksByTitle(title);
            }
            if(query.equals("author")){
                return bookService.getBooksByAuthor(author);
            }
        }
        return bookService.getBooks();
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<BookDto> saveBook(@Valid @RequestBody  BookDto bookDto){
        bookService.createBook(bookDto.getIsbn(),bookDto.getTitle(),bookDto.getAuthor());
        return  ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @PostMapping("/favourite-add")
    @ResponseBody
    public BookDto addBookToFavourite( @RequestBody  BookDto bookDto){
        return  bookService.addBookToFavourite(bookDto);
    }

    @PostMapping("/favourite-delete")
    @ResponseBody
    public BookDto deleteBookFromFavourite(@RequestBody  BookDto bookDto){
        return  bookService.deleteBookFromFavourite(bookDto);
    }

    @GetMapping("/favourite-list")
    public String favouritePage(Model model){
        List<BookDto> favouriteBooks = bookService.getFavouriteBooks();
        System.out.println(favouriteBooks);
        model.addAttribute("favouriteBooks",favouriteBooks);
        return "book-favourite";
    }

    @GetMapping("/{isbn}")
    public String singlePage(Model model, @PathVariable(value="isbn") String isbn){
        BookDto bookDto = bookService.getBookByIsbn(isbn);
        System.out.println(bookDto);
        model.addAttribute("bookinfo",bookDto);
        return "book-page";
    }



}
