package com.example.todo.controllers;

import com.example.todo.dto.request.UpdateUserRequest;
import com.example.todo.dto.response.UserResponse;
import com.example.todo.entities.User;
import com.example.todo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request, Principal principal) {
        User currentUser = userService.getUserByEmail(principal.getName());

        if(!currentUser.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update users with id: " + id);
        }

        User updatedUser = userService.updateUser(id, request);

        return ResponseEntity.ok(new UserResponse(updatedUser.getId(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getBio(),
                updatedUser.getEmail()));
    }
}
