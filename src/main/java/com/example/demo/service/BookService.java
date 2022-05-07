package com.example.demo.service;

import com.example.demo.entity.BookDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public BookDto createBook(String isbn, String title, String author) {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn(isbn);
        bookDto.setTitle(title);
        bookDto.setAuthor(author);
        return bookRepository.saveAndFlush(bookDto);
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

    @Transactional
    public BookDto addBookToFavourite(BookDto bookDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            UserEntity user = userRepository.findByLogin(username).orElse(null);
            if(user.getFavouriteBooks().contains(bookDto)){
                return null;
            }
            else {
                user.getFavouriteBooks().add(bookDto);
                System.out.println("List of favourite books:" + user.getFavouriteBooks());
                return bookDto;
            }
        }
        else return null;
    }

    @Transactional
    public BookDto deleteBookFromFavourite(BookDto bookDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            UserEntity user = userRepository.findByLogin(username).orElse(null);
            if(user.getFavouriteBooks().contains(bookDto)){
                user.getFavouriteBooks().remove(bookDto);
                System.out.println("List of favourite books:" + user.getFavouriteBooks());
                return bookDto;
            }
            else {
                return null;
            }
        }
        else return null;
    }

    public List<BookDto> getFavouriteBooks(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            UserEntity user = userRepository.findByLogin(username).orElse(null);
            return user.getFavouriteBooks();
        }
        return null;
    }





}
