package com.tasks.category_service.service;

import com.tasks.category_service.model.Category;
import com.tasks.category_service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        // Aquí podríamos añadir lógica de negocio, ej. validar que el nombre no esté vacío
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty.");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category existingCategory = categoryOptional.get();
            if (categoryDetails.getName() != null && !categoryDetails.getName().trim().isEmpty()) {
                existingCategory.setName(categoryDetails.getName());
            }
            if (categoryDetails.getDescription() != null) {
                existingCategory.setDescription(categoryDetails.getDescription());
            }
            return categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found with id " + id);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        // Aquí podrías añadir lógica para manejar tareas asociadas a esta categoría
        // Por ahora, solo eliminará la categoría.
        // Más adelante: ¿Deberíamos verificar si hay tareas asociadas antes de eliminar?
        // ¿Qué hacemos con esas tareas? (desasignarlas, eliminarlas, etc.)
        categoryRepository.deleteById(id);
    }
}