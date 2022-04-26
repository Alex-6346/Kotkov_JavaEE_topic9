package com.example.demo.service;

import com.example.demo.model.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {


    private final EntityManager entityManager;

    @Transactional
    public BookDto createBook(String isbn, String title, String author) {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn(isbn);
        bookDto.setTitle(title);
        bookDto.setAuthor(author);

        return entityManager.merge(bookDto);
    }


    @Transactional
    public List<BookDto> getBooks(){
        return entityManager.createQuery("SELECT b FROM books b", BookDto.class).getResultList();
    }


    @Transactional
    public BookDto getBookByIsbn(String isbn){
        return entityManager.find(BookDto.class,isbn);
    }

    @Transactional
    public List<BookDto> getBooksByTitle(String title){
        return entityManager.createQuery("SELECT b FROM books b WHERE b.title LIKE :query", BookDto.class)
                .setParameter("query", "%"+title+"%").getResultList();
    }

    @Transactional
    public List<BookDto> getBooksByAuthor(String author){
        return entityManager.createQuery("SELECT b FROM books b WHERE b.author LIKE :query", BookDto.class)
                .setParameter("query", "%"+author+"%").getResultList();
    }




}
