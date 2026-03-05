package org.angelo.todolist.users;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.angelo.todolist.auth.RegisterRequest;
import org.angelo.todolist.exceptions.DuplicateEmailException;
import org.angelo.todolist.exceptions.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateEmailException("Email already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found" + id));
    }
}
