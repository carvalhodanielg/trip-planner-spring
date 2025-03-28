package com.example.todo.services;

import com.example.todo.entities.Todo;
import com.example.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id){
         todoRepository.deleteById(id);
    }

    public Todo updateTodo(Long id, Todo editedTodo){
        return todoRepository.findById(id).map(todo -> {
            todo.setTitle(editedTodo.getTitle());
            todo.setDescription(editedTodo.getDescription());
            todo.setCompleted(editedTodo.isCompleted());
            return  todoRepository.save(todo);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Todo toggleTodoStatus(Long id, Boolean completed){
        return todoRepository.findById(id).map(todo -> {
            todo.setCompleted(completed);
            return todoRepository.save(todo);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }


}
