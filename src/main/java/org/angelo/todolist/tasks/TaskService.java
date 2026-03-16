package org.angelo.todolist.tasks;

import lombok.RequiredArgsConstructor;
import org.angelo.todolist.exceptions.InvalidCredentialsException;
import org.angelo.todolist.exceptions.ResourceNotFoundException;
import org.angelo.todolist.users.User;
import org.angelo.todolist.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public TaskResponse createTask(TaskRequest request, String email){
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setCompleted(false);
        task.setUser(user);

        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse updateTask(Long id, TaskRequest request, String email) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if(!task.getUser().getEmail().equals(email)) {
            throw new InvalidCredentialsException("You don't have permission to modify this task");
        }
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setCompleted(request.completed());
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }

    public List<Task> getTasksByUser(String email) {
        return taskRepository.findByUserEmail(email);
    }

    public List<Task> getTasksByStatus(boolean status) {return taskRepository.findByCompleted(status);}
    public List<Task> searchTasks(String title) {return taskRepository.findByTitleContaining(title);}

    public void deleteTask(Long id, String email) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if(!task.getUser().getEmail().equals(email)) {
            throw new InvalidCredentialsException("You don't have permission to modify this task");
        }
        taskRepository.delete(task);
    }
}

