package com.tasks.category_service.repository;


import com.tasks.category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Métodos CRUD básicos heredados
}