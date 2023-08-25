package com.sanda.prog2.repository;

import com.sanda.prog2.model.Category;
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
public class CategoryRepository {
    private Connection connection;
    private Category newCategory(ResultSet resultSet) throws SQLException {
        return new Category(
            resultSet.getInt("id_category"),
            resultSet.getString("name")
        );
    }
    public List<Category> getALlCategory() throws SQLException{
        String query = "SELECT * FROM \"category\"";
        ResultSet resultSet = this.connection.createStatement().executeQuery(query);
        List<Category> listCategory = new ArrayList<>();
        while(resultSet.next()){
            listCategory.add(this.newCategory(resultSet));
        }
        return listCategory;
    }

    public Category getCategoryById(Integer id) throws SQLException {
        String query = "SELECT * FROM \"category\" WHERE \"id_category\" = ? ";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return this.newCategory(resultSet);
        }
        return null;
    }

    public Category deleteCategory(Integer idCategory) throws SQLException {
        Category category = this.getCategoryById(idCategory);
        if( category != null){
            String query = "DELETE FROM \"category\" WHERE \"id_category\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,idCategory);
            statement.executeUpdate();
        }
        return category;
    }

    public Category updateCategory(Category category) throws SQLException {
        if(this.getCategoryById(category.getIdCategory()) != null){
            String query = "UPDATE \"category\" SET \"name\" = ?  WHERE \"id_category\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,category.getName());
            statement.setInt(2,category.getIdCategory());
            statement.executeUpdate();
            return category;
        }
        return null;
    }

    public Category createCategory(Category category) throws SQLException {
        String query = "INSERT INTO \"category\"(\"name\") VALUES (?,?)";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,category.getName());
        statement.executeUpdate();
        String queryNewCategory = "SELECT * FROM \"category\" ORDER BY \"id_category\" DESC LIMIT 1";
        ResultSet resultSet = this.connection.createStatement().executeQuery(queryNewCategory);
        resultSet.next();
        return this.newCategory(resultSet);
    }
}
