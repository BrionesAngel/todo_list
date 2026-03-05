package org.angelo.todolist.users;

import lombok.RequiredArgsConstructor;
import org.angelo.todolist.auth.RegisterRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {return userService.getUser(id);}
}
