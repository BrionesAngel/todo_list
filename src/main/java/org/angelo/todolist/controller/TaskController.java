package org.angelo.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.angelo.todolist.model.Task;
import org.angelo.todolist.service.TaskService;
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

    @GetMapping
    public List<Task> getAllTask(){
        return taskService.getAllTasks();
    }
}

