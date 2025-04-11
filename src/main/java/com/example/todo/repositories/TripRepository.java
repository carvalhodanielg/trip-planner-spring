package com.example.todo.repositories;

import com.example.todo.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
