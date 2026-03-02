package org.angelo.todolist.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task){ return taskRepository.save(task);}
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public List<Task> getTasksByStatus(boolean status) {return taskRepository.findByCompleted(status);}
    public List<Task> searchTasks(String title) {return taskRepository.findByTitleContaining(title);}
}
