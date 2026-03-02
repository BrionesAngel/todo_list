package org.angelo.todolist.users;

import lombok.RequiredArgsConstructor;
import org.angelo.todolist.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    public User createUser(User user){ return userRepository.save(user);}
    public User getUser(Long id){
        return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found" + id));
    }
}
