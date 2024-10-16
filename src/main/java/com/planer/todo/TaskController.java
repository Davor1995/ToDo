package com.planer.todo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    // Task erstellen
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
    // Alle Tasks abholen
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Einen Task nach ID aussuchen
    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable String id) {
        return taskRepository.findById(id);
    }

    // Einen Task aktualisieren
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setCompleted(updatedTask.isCompleted());
        return taskRepository.save(updatedTask);
        }).orElseGet(() -> {
                updatedTask.setId(id);
                return taskRepository.save(updatedTask);
        });
    }

    // Aufgabe löschen
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") String id) {
        taskRepository.deleteById(id);
    }

}
