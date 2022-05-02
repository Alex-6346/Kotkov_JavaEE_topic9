package com.example.demo.service;

import com.example.demo.model.BookDto;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;

    @Transactional
    public BookDto createBook(String isbn, String title, String author) {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn(isbn);
        bookDto.setTitle(title);
        bookDto.setAuthor(author);

        return bookRepository.saveAndFlush(bookDto).orElse(null);
    }


    @Transactional
    public List<BookDto> getBooks(){
          return bookRepository.findAll();
    }


    @Transactional
    public BookDto getBookByIsbn(String isbn){
        return bookRepository.findByIsbnIgnoreCase(isbn).orElse(null);
    }

    @Transactional
    public List<BookDto> getBooksByTitle(String title){
          return bookRepository.findAllByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public List<BookDto> getBooksByAuthor(String author){
         return bookRepository.findAllByAuthorContainingIgnoreCase(author);
    }




}
