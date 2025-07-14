package com.tasks.task_service.service;


import java.util.List;
import java.util.Optional;
import com.tasks.task_service.model.Task;

public interface TaskService {
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
}