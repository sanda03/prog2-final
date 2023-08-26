package com.sanda.prog2.repository;

import com.sanda.prog2.model.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorRepositoryInterface {
    List<Author> getAllAuthors() throws SQLException;
    Author getAuthorById(Integer id) throws SQLException;
    Author deleteAuthor(Integer id) throws SQLException;
    Author updateAuthor(Author author) throws SQLException;
    Author updatePartialAuthor(Author author) throws SQLException;
    Author createAuthor(Author author) throws SQLException;
}
