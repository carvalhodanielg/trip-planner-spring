package com.example.todo.repositories;

import com.example.todo.entities.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportationRepository extends JpaRepository<Transportation, Long> {

    List<Transportation> findByTripId(Long id);
}
