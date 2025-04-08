package com.example.todo.dto.response;

public record LoginResponse(String token, String email,String firstName,String lastName) {
}
