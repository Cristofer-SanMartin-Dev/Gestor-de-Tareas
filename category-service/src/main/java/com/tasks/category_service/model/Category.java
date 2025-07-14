package com.tasks.category_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories") // Tabla para categorías
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Nombre de categoría no puede ser nulo y debe ser único
    private String name;

    private String description; // Descripción opcional de la categoría
}
