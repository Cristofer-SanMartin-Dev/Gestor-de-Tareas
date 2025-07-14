package com.tasks.task_service.service;



import com.tasks.task_service.model.Task;
import com.tasks.task_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta clase es un componente de servicio de Spring
public class TaskServiceImpl implements TaskService {

    @Autowired // Inyecta una instancia de TaskRepository
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(Task task) {
        // Aquí podríamos añadir lógica de negocio antes de guardar, por ejemplo:
        // - Validar campos específicos
        // - Establecer valores por defecto (ej. fecha de creación)
        // if (task.getStatus() == null) {
        //     task.setStatus(TaskStatus.PENDING);
        // }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();
            // Actualizar solo los campos que vienen en taskDetails y no son nulos
            if (taskDetails.getTitle() != null) {
                existingTask.setTitle(taskDetails.getTitle());
            }
            if (taskDetails.getDescription() != null) {
                existingTask.setDescription(taskDetails.getDescription());
            }
            if (taskDetails.getStatus() != null) {
                existingTask.setStatus(taskDetails.getStatus());
            }
            if (taskDetails.getDueDate() != null) {
                existingTask.setDueDate(taskDetails.getDueDate());
            }
            if (taskDetails.getPriority() != null) {
                existingTask.setPriority(taskDetails.getPriority());
            }

            // Aquí es donde en el futuro podríamos añadir lógica para categoryId o tags
            // if (taskDetails.getCategoryId() != null) {
            //     existingTask.setCategoryId(taskDetails.getCategoryId());
            // }
            // if (taskDetails.getTagIds() != null) {
            //     existingTask.setTagIds(taskDetails.getTagIds());
            // }

            // Aquí podrías añadir lógica de negocio, por ejemplo,
            // si el estado cambia a COMPLETED, registrar una fecha de finalización.

            return taskRepository.save(existingTask);
        } else {
            // Podríamos lanzar una excepción personalizada aquí si la tarea no existe
            throw new RuntimeException("Task not found with id " + id);
        }
    }

    @Override
    public void deleteTask(Long id) {
        // Aquí podríamos añadir lógica de negocio antes de eliminar, por ejemplo:
        // - Verificar permisos
        // - Eliminar tareas relacionadas en otros sistemas
        taskRepository.deleteById(id);
    }
}