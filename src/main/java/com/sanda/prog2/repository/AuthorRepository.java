package com.sanda.prog2.repository;

import com.sanda.prog2.model.Author;
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
public class AuthorRepository implements AuthorRepositoryInterface{
    private Connection connection;
    private Author newAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
            resultSet.getInt("id_author"),
            resultSet.getString("name"),
            resultSet.getString("first_name")
        );
    }

    private String createQuery(Author author){
        StringBuilder query = new StringBuilder("SET ");
        boolean status = false;
        if(author.getName() != null){
            query.append("\"name\" = ? ");
            status = true;
        }
        if(author.getFirstName() != null){
            if(status)
                query.append(", ");
            query.append("\"first_name\" = ? ");
        }
        return query.toString();
    }

    @Override
    public List<Author> getAllAuthors() throws SQLException{
        String query = "SELECT * FROM \"author\"";
        ResultSet resultSet = this.connection.createStatement().executeQuery(query);
        List<Author> listAuthors = new ArrayList<>();
        while(resultSet.next()){
            listAuthors.add(this.newAuthor(resultSet));
        }
        return listAuthors;
    }

    @Override
    public Author getAuthorById(Integer id) throws SQLException {
        String query = "SELECT * FROM \"author\" WHERE \"id_author\" = ? ";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return this.newAuthor(resultSet);
        }
        return null;
    }

    @Override
    public Author deleteAuthor(Integer idAuthor) throws SQLException {
        Author author = this.getAuthorById(idAuthor);
        if( author != null){
            String query = "DELETE FROM \"author\" WHERE \"id_author\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,idAuthor);
            statement.executeUpdate();
        }
        return author;
    }

    @Override
    public Author updateAuthor(Author author) throws SQLException {
        if(this.getAuthorById(author.getIdAuthor()) != null){
            String query = "UPDATE \"author\" SET \"name\" = ? , \"first_name\" = ? WHERE \"id_author\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,author.getName());
            statement.setString(2,author.getFirstName());
            statement.setInt(3,author.getIdAuthor());
            statement.executeUpdate();
            return author;
        }
        return null;
    }

    @Override
    public Author updatePartialAuthor(Author author) throws SQLException {
        if(this.getAuthorById(author.getIdAuthor()) != null) {
            int valueIndex = 0;
            String query = "UPDATE \"author\" " + createQuery(author) + " WHERE \"id_author\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            if(author.getName() != null)
                statement.setString(++valueIndex, author.getName());
            if(author.getFirstName() != null)
                statement.setString(++valueIndex, author.getFirstName());
            statement.setInt(++valueIndex, author.getIdAuthor());
            statement.executeUpdate();
            return this.getAuthorById(author.getIdAuthor());
        }
        return null;
    }

    @Override
    public Author createAuthor(Author author) throws SQLException {
        String query = "INSERT INTO \"author\"(\"name\",\"first_name\") VALUES (?,?)";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,author.getName());
        statement.setString(2,author.getFirstName());
        statement.executeUpdate();
        String queryNewAuthor = "SELECT * FROM \"author\" ORDER BY \"id_author\" DESC LIMIT 1";
        ResultSet resultSet = this.connection.createStatement().executeQuery(queryNewAuthor);
        resultSet.next();
        return this.newAuthor(resultSet);
    }
}
