package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(value = "/books", produces = "text/html")
    public String viewBooksPage(Model model) {
        List<Book> books = service.findAllBooks();
        model.addAttribute("books", books);
        return "books/books";
    }

    @GetMapping(value = "/books/{id}", produces = "text/html")
    public ModelAndView getBookById(@RequestParam long id) {
        Book book = service.findBookById(id);
        ModelAndView mav = new ModelAndView("books/result");
        mav.addObject("book", book);
        return mav;
    }

    @GetMapping(value = "/books/new-book", produces = "text/html")
    public String showNewForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/new-book";
    }

    @PostMapping(value = "/books", produces = "text/html")
    public String save(@ModelAttribute("book") Book book) {
        service.createBook(book);
        return "redirect:/books";
    }

    @PutMapping(value = "/books/{id}", produces = "text/html")
    public ModelAndView showEditForm(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("books/edit-book");
        Book book = service.findBookById(id);
        mav.addObject("book", book);
        return mav;
    }

    @PutMapping(value = "/books", produces = "text/html")
    public String update(@ModelAttribute("book") Book book) {
        service.updateBook(book);
        return "redirect:/books";
    }

    @DeleteMapping(value = "/books/{id}", produces = "text/html")
    public String delete(@PathVariable(name = "id") long id) {
        service.deleteBook(id);
        return "redirect:/books";
    }
}
