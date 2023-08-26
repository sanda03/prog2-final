package com.sanda.prog2.repository;

import com.sanda.prog2.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookRepositoryInterface {
    List<Book> getAllBooks() throws SQLException;
    Book getBookById(Integer id) throws SQLException;
    Book deleteBook(Integer id) throws SQLException;
    Book updateBook(Book book) throws SQLException;
    Book updatePartialBook(Book book) throws SQLException;
    Book createBook(Book book) throws SQLException;
}
