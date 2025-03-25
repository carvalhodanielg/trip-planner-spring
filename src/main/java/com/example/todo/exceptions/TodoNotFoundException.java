package com.example.todo.exceptions;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id){
        super("To-do not found with ID: " + id);
    }
}
