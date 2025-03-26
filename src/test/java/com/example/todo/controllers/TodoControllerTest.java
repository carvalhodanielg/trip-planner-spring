package com.example.todo.controllers;

import com.example.todo.entities.Todo;
import com.example.todo.exceptions.TodoNotFoundException;
import com.example.todo.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTodos_ShouldReturnAllTodos(){
        Todo todo1 = new Todo("first task", "first description",false);
        Todo todo2 = new Todo("second task", "yet another task", true );

        List<Todo> expectedTodos = Arrays.asList(todo1, todo2);

        when(todoService.getAllTodos()).thenReturn(expectedTodos);

        List<Todo> actualTodos = todoController.getAllTodos();

        assertEquals(expectedTodos, actualTodos);
        verify(todoService, times(1)).getAllTodos();

    }

    @Test
    void createTodo_ShouldReturnCreatedTodo(){
        Todo todoToCreate = new Todo("first task", "first description",false);

        when(todoService.createTodo(todoToCreate)).thenReturn(todoToCreate);

        Todo createdTodo = todoController.createTodo(todoToCreate);

        assertEquals(todoToCreate, createdTodo);
        verify(todoService, times(1)).createTodo(todoToCreate);

    }

    @Test
    void updateTodo_WithValidId_ShouldReturnUpdatedTodo() {
        Long id = 1L;
        Todo editedTodo = new Todo("Todo 1", "Updated Task", false);
        Todo updatedTodo = new Todo("Todo 1", "Updated Task", false);

        when(todoService.updateTodo(id, editedTodo)).thenReturn(updatedTodo);

        ResponseEntity<Todo> response = todoController.updateTodo(id, editedTodo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTodo, response.getBody());
        verify(todoService, times(1)).updateTodo(id, editedTodo);
    }
    @Test
    void updateTodo_WithInvalidId_ShouldThrowException() {
        Long invalidId = 99L;
        Todo editedTodo = new Todo("Title", "Non-existent Task", false);

        when(todoService.updateTodo(invalidId, editedTodo))
                .thenThrow(new TodoNotFoundException(invalidId));

        assertThrows(TodoNotFoundException.class, () -> {
            todoController.updateTodo(invalidId, editedTodo);
        });
        verify(todoService, times(1)).updateTodo(invalidId, editedTodo);
    }

    @Test
    void deleteTodo_WithInvalidId_ShouldThrowException() {
       Long invalidId = 99L;
        doThrow(new TodoNotFoundException(invalidId)).when(todoService).deleteTodo(invalidId);

        assertThrows(TodoNotFoundException.class, () -> {
            todoController.deleteTodo(invalidId);
        });
        verify(todoService, times(1)).deleteTodo(invalidId);
    }




}
