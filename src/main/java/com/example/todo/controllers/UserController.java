package com.example.todo.controllers;

import com.example.todo.dto.response.UserResponse;
import com.example.todo.entities.User;
import com.example.todo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getBio(),  user.getEmail()));
    }
}
