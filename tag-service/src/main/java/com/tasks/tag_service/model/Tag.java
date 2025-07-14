package com.tasks.tag_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tags") // Tabla para etiquetas
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Nombre de etiqueta no puede ser nulo y debe ser único
    private String name;

    private String description; // Descripción opcional de la etiqueta
}