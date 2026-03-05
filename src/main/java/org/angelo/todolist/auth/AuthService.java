package org.angelo.todolist.auth;

import lombok.RequiredArgsConstructor;
import org.angelo.todolist.users.User;
import org.angelo.todolist.users.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request) {

        User user = userService.createUser(request);
        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}