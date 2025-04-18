package com.example.todo.repositories;

import com.example.todo.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
//    Fornece métodos como save, findById, findAll, delete etc
//    Aqui criamos métodos personalizados, como um findByCompleted(boolean completed)
}
