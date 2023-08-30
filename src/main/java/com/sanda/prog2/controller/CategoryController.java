package com.sanda.prog2.controller;

import com.sanda.prog2.model.Category;
import com.sanda.prog2.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategory(HttpServletResponse response){
        return this.categoryService.getAllCategory(response);
    }

    @GetMapping("/{idCategory}")
    public Category getCategoryById(HttpServletResponse response, @PathVariable Integer idCategory){
        return this.categoryService.getCategoryById(response,idCategory);
    }

    @DeleteMapping("/{idCategory}")
    public Category deleteCategory(HttpServletResponse response, @PathVariable Integer idCategory){
        return this.categoryService.deleteCategory(response,idCategory);
    }

    @PutMapping
    public Category updateCategory(HttpServletResponse response, @RequestBody Category category){
        return this.categoryService.updateCategory(response,category);
    }

    @PatchMapping
    public Category updatePartialCategory(HttpServletResponse response, @RequestBody Category category){
        return this.categoryService.updateCategory(response,category);
    }

    @PostMapping
    public Category createCategory(HttpServletResponse response, @RequestBody Category category){
        return this.categoryService.createCategory(response,category);
    }
}
