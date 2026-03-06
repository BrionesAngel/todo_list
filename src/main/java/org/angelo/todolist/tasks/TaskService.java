package org.angelo.todolist.tasks;

import lombok.RequiredArgsConstructor;
import org.angelo.todolist.exceptions.ResourceNotFoundException;
import org.angelo.todolist.users.User;
import org.angelo.todolist.users.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public TaskResponse createTask(TaskRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        String email = auth.getName();

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

    public List<Task> getTasksByStatus(boolean status) {return taskRepository.findByCompleted(status);}
    public List<Task> searchTasks(String title) {return taskRepository.findByTitleContaining(title);}
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}

