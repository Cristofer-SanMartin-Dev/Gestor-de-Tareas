package com.tasks.task_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tasks.task_service.model.Task;

@Repository // Indica que esta interfaz es un componente de Spring para acceso a datos
public interface TaskRepository extends JpaRepository<Task, Long> {
    // JpaRepository ya nos da métodos CRUD básicos (save, findById, findAll, deleteById, etc.)
    // Si necesitamos métodos de consulta más específicos, los podemos definir aquí.
    // Por ejemplo: List<Task> findByStatus(TaskStatus status);
}