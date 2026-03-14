package org.angelo.todolist.auth;

import lombok.RequiredArgsConstructor;
import org.angelo.todolist.exceptions.UserNotFoundException;
import org.angelo.todolist.users.User;
import org.angelo.todolist.users.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String email) throws UserNotFoundException {
        System.out.println(">>> Buscando usuario con email: '" + email + "'"); // email buscado
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                java.util.List.of()
        );
    }
}