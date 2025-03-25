package com.example.todo.service;

import com.example.todo.entities.Todo;
import com.example.todo.repositories.TodoRepository;
import com.example.todo.services.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void testCreateTodo(){
        Todo todo = new Todo("Primeiro", "Primeiro todo-teste", false);
        when(todoRepository.save(todo)).thenReturn(todo);

        Todo createdTodo = todoService.createTodo(todo);
        assertEquals(todo.getTitle(), createdTodo.getTitle());
        verify(todoRepository, times(1)).save(todo);
    }
}
