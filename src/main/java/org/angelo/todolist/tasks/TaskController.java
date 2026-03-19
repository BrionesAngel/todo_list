package org.angelo.todolist.tasks;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request, @AuthenticationPrincipal UserDetails userDetails){
        return taskService.createTask(request, userDetails.getUsername());
    }

    @GetMapping
    public List<Task> getTasks(@AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getTasksByUser(userDetails.getUsername()); // username = email
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@Valid @PathVariable Long id, @RequestBody TaskRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.updateTask(id, request, userDetails.getUsername());
    }

    @GetMapping("/completed/{status}")
    public List<Task> getTasksByStatus(@PathVariable boolean status) {return taskService.getTasksByStatus(status); }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String title) {return taskService.searchTasks(title);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        taskService.deleteTask(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}

