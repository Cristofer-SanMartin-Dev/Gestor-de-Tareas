package com.tasks.task_service.controller;

import com.tasks.task_service.model.Task;
import com.tasks.task_service.service.TaskService; // Importa la interfaz del servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // Permite peticiones de cualquier origen
public class TaskController {

    // Inyecta la interfaz del servicio, no el repositorio directamente
    @Autowired
    private TaskService taskService; // Ahora inyectamos el servicio

    // --- ENDPOINTS PARA TAREAS ---

    // 1. Obtener todas las tareas
    // GET http://localhost:8080/api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks(); // Llama al servicio
    }

    // 2. Obtener una tarea por ID
    // GET http://localhost:8080/api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id); // Llama al servicio
        return task.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva tarea
    // POST http://localhost:8080/api/tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.createTask(task); // Llama al servicio
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // 4. Actualizar una tarea existente
    // PUT http://localhost:8080/api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        try {
            Task updatedTask = taskService.updateTask(id, taskDetails); // Llama al servicio
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) { // Captura la excepción si la tarea no se encuentra
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Eliminar una tarea
    // DELETE http://localhost:8080/api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id); // Llama al servicio
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) { // Podríamos ser más específicos con el tipo de excepción
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}