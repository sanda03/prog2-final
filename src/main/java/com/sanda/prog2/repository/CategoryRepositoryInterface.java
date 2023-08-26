package com.sanda.prog2.repository;

import com.sanda.prog2.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryRepositoryInterface {
    List<Category> getAllCategory() throws SQLException;
    Category getCategoryById(Integer id) throws SQLException;
    Category deleteCategory(Integer id) throws SQLException;
    Category updateCategory(Category category) throws SQLException;
    Category createCategory(Category category) throws SQLException;
}
