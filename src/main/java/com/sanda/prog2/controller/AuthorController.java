package com.sanda.prog2.controller;

import com.sanda.prog2.model.Author;
import com.sanda.prog2.service.AuthorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors(HttpServletResponse response){
        return this.authorService.getAllAuthors(response);
    }

    @GetMapping("/{idAuthor}")
    public Author getAuthorById(HttpServletResponse response, @PathVariable Integer idAuthor){
        return this.authorService.getAuthorById(response,idAuthor);
    }

    @DeleteMapping("/{idAuthor}")
    public Author deleteAuthor(HttpServletResponse response, @PathVariable Integer idAuthor){
        return this.authorService.deleteAuthor(response,idAuthor);
    }

    @PutMapping
    public Author updateAuthor(HttpServletResponse response, @RequestBody Author author){
        return this.authorService.updateAuthor(response,author);
    }

    @PatchMapping
    public Author updatePartialAuthor(HttpServletResponse response, @RequestBody Author author){
        return this.authorService.updatePartialAuthor(response,author);
    }

    @PostMapping
    public Author createAuthor(HttpServletResponse response, @RequestBody Author author){
        return this.authorService.createAuthor(response,author);
    }
}
