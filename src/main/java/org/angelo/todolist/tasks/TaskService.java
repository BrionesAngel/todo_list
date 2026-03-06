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

    public Task createTask(Task task){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        String email = auth.getName();

        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        task.setUser(user);
        return taskRepository.save(task);
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

