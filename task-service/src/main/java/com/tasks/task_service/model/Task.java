package com.tasks.task_service.model;

import jakarta.persistence.*;
import lombok.Data; // Si usaste Lombok
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate; // Para la fecha de vencimiento

@Data // Lombok: Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Genera constructor sin argumentos
@AllArgsConstructor // Lombok: Genera constructor con todos los argumentos
@Entity // Indica que esta clase es una entidad JPA y se mapeará a una tabla
@Table(name = "tasks") // Define el nombre de la tabla en la base de datos
public class Task {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de IDs (auto-incremento)
    private Long id;

    @Column(nullable = false) // Indica que esta columna no puede ser nula
    private String title;

    @Column(length = 1000) // Define la longitud máxima de la columna
    private String description;

    @Enumerated(EnumType.STRING) // Mapea el Enum como String en la BD
    private TaskStatus status; // Usaremos un Enum para los estados

    private LocalDate dueDate; // Fecha de vencimiento (utiliza java.time.LocalDate)

    @Enumerated(EnumType.STRING) // Mapea el Enum como String en la BD
    private TaskPriority priority; // Usaremos un Enum para las prioridades

    // Más adelante agregaremos categoryId y tags aquí
    // private Long categoryId;
    // private Set<Long> tagIds;
}

// --- ENUMS (Agregar estas clases al mismo archivo Task.java por simplicidad, o en archivos separados) ---
// Puedes ponerlos en el mismo archivo por ahora, o crear un nuevo paquete 'enums'
enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED
}

enum TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}