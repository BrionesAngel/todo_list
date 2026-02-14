package org.angelo.todolist.service;

import lombok.RequiredArgsConstructor;
import org.angelo.todolist.model.Task;
import org.angelo.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
