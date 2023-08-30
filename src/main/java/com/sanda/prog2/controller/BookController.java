package com.sanda.prog2.controller;

import com.sanda.prog2.model.Book;
import com.sanda.prog2.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(HttpServletResponse response){
        return this.bookService.getAllBooks(response);
    }

    @GetMapping("/{idBook}")
    public Book getBookById(HttpServletResponse response, @PathVariable Integer idBook){
        return this.bookService.getBookById(response,idBook);
    }

    @DeleteMapping("/{idBook}")
    public Book deleteBook(HttpServletResponse response, @PathVariable Integer idBook){
        return this.bookService.deleteBook(response,idBook);
    }

    @PutMapping
    public Book updateBook(HttpServletResponse response, @RequestBody Book book){
        return this.bookService.updateBook(response,book);
    }

    @PatchMapping
    public Book updatePartialBook(HttpServletResponse response, @RequestBody Book book){
        return this.bookService.updatePartialBook(response,book);
    }

    @PostMapping
    public Book createBook(HttpServletResponse response, @RequestBody Book book){
        return this.bookService.createBook(response,book);
    }
}
