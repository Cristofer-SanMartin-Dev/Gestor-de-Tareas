package com.tasks.tag_service.repository;


import com.tasks.tag_service.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    // Métodos CRUD básicos heredados
}