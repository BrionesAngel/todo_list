package org.angelo.todolist.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task){

        return taskService.createTask(task);
    }

    @GetMapping("/completed/{status}")
    public List<Task> getTasksByStatus(@PathVariable boolean status) {return taskService.getTasksByStatus(status); }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String title) {return taskService.searchTasks(title);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

