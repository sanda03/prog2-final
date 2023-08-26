package com.sanda.prog2.repository;

import com.sanda.prog2.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class BookRepository {
    private Connection connection;
    private Book newBook(ResultSet resultSet) throws SQLException {
        return new Book(
            resultSet.getInt("id_book"),
            resultSet.getString("title"),
            resultSet.getString("description"),
            resultSet.getInt("id_author"),
            resultSet.getInt("id_category")
        );
    }
    private String createQuery(Book book){
        StringBuilder query = new StringBuilder("SET ");
        boolean status = false;
        if(book.getTitle() != null){
            query.append("\"title\" = ? ");
            status = true;
        }
        if(book.getDescription() != null){
            if(status)
                query.append(", ");
            query.append("\"description\" = ? ");
            status = true;
        }
        if(book.getIdAuthor() != null){
            if(status)
                query.append(", ");
            query.append("\"id_author\" = ? ");
            status = true;
        }
        if(book.getIdCategory() != null){
            if(status)
                query.append(", ");
            query.append("\"id_category\" = ? ");
        }
        return query.toString();
    }
    public List<Book> getAllBooks() throws SQLException{
        String query = "SELECT * FROM \"book\"";
        ResultSet resultSet = this.connection.createStatement().executeQuery(query);
        List<Book> listBooks = new ArrayList<>();
        while(resultSet.next()){
            listBooks.add(this.newBook(resultSet));
        }
        return listBooks;
    }

    public Book getBookById(Integer id) throws SQLException {
        String query = "SELECT * FROM \"book\" WHERE \"id_book\" = ? ";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return this.newBook(resultSet);
        }
        return null;
    }

    public Book deleteBook(Integer idBook) throws SQLException {
        Book book = this.getBookById(idBook);
        if( book != null){
            String query = "DELETE FROM \"book\" WHERE \"id_book\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,idBook);
            statement.executeUpdate();
        }
        return book;
    }

    public Book updateBook(Book book) throws SQLException {
        if(this.getBookById(book.getIdBook()) != null){
            String query = "UPDATE \"book\" SET \"title\" = ? , \"description\" = ?, \"id_author\" = ? , \"id_category\" = ?" +
             " WHERE \"id_book\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getDescription());
            statement.setInt(3,book.getIdAuthor());
            statement.setInt(4,book.getIdCategory());
            statement.setInt(5,book.getIdBook());
            statement.executeUpdate();
            return book;
        }
        return null;
    }

    public Book updatePartialBook(Book book) throws SQLException {
        if(this.getBookById(book.getIdBook()) != null) {
            int valueIndex = 0;
            String query = "UPDATE \"book\" " + createQuery(book) + " WHERE \"id_book\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            if(book.getTitle() != null)
                statement.setString(++valueIndex, book.getTitle());
            if(book.getDescription() != null)
                statement.setString(++valueIndex, book.getDescription());
            if(book.getIdAuthor() != null)
                statement.setInt(++valueIndex, book.getIdAuthor());
            if(book.getIdCategory() != null)
                statement.setInt(++valueIndex, book.getIdCategory());
            statement.setInt(++valueIndex, book.getIdBook());
            statement.executeUpdate();
            return this.getBookById(book.getIdBook());
        }
        return null;
    }

    public Book createBook(Book book) throws SQLException {
        String query = "INSERT INTO \"book\"(\"title\",\"description\",\"id_author\",\"id_category\") VALUES (?,?,?,?)";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,book.getTitle());
        statement.setString(2,book.getDescription());
        statement.setInt(3,book.getIdAuthor());
        statement.setInt(4,book.getIdCategory());
        statement.executeUpdate();
        String queryNewBook = "SELECT * FROM \"book\" ORDER BY \"id_book\" DESC LIMIT 1";
        ResultSet resultSet = this.connection.createStatement().executeQuery(queryNewBook);
        resultSet.next();
        return this.newBook(resultSet);
    }
}
