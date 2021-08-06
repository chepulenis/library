package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository repository;

    public Book findBookById(long id) {
        Book book = repository.findById(id).orElse(null);
        logger.info("Book {} by id {} found", book, id);
        return book;
    }

    public List<Book> findAllBooks() {
        List<Book> books = repository.findAll();
        logger.info("All books {} have been found", books);
        return books;
    }

    public void deleteBook(long id) {
        logger.info("Book {} deleted", id);
        repository.deleteById(id);
    }

    public Book updateBook(Book book) {
        logger.info("Book {} updated", book);
        repository.save(book);
        return book;
    }

    public Book createBook(Book book) {
        logger.info("Book {} created", book);
        repository.save(book);
        return book;
    }

}
