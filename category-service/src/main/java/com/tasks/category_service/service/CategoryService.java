package com.tasks.category_service.service;

import com.tasks.category_service.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category categoryDetails);
    void deleteCategory(Long id);
}