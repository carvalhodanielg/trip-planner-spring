package com.example.todo.dto.request;

public record UpdateUserRequest(String firstName, String lastName, String email, String bio) {
}
