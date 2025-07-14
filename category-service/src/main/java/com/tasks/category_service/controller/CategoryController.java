package com.tasks.category_service.controller;


import com.tasks.category_service.model.Category;
import com.tasks.category_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories") // URL base para categorías
@CrossOrigin(origins = "*") // Permite peticiones de cualquier origen
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // --- ENDPOINTS PARA CATEGORÍAS ---

    // 1. Obtener todas las categorías
    // GET http://localhost:8081/api/categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 2. Obtener una categoría por ID
    // GET http://localhost:8081/api/categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva categoría
    // POST http://localhost:8081/api/categories
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.createCategory(category);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Devuelve 400 Bad Request si el nombre es vacío
        }
    }

    // 4. Actualizar una categoría existente
    // PUT http://localhost:8081/api/categories/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        try {
            Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
            return ResponseEntity.ok(updatedCategory);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 si no encuentra la categoría
        }
    }

    // 5. Eliminar una categoría
    // DELETE http://localhost:8081/api/categories/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 si hay otro error
        }
    }
}