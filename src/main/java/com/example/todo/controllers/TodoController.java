package com.example.todo.controllers;

import com.example.todo.dto.TodoRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.entities.Todo;
import com.example.todo.exceptions.TodoNotFoundException;
import com.example.todo.services.TodoService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo", description = "Endpoint to handle Tasks")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiResponse(responseCode = "200", description = "List all tasks [description]")
    @Operation(summary = "List all tasks [summary]")
    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo editedTodo){
        return ResponseEntity.ok(todoService.updateTodo(id, editedTodo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<Todo> toggleTodoStatus(
            @PathVariable Long id,
            @RequestParam(required = false) Boolean completed){
        return ResponseEntity.ok(todoService.toggleTodoStatus(id, completed));
    }
}

