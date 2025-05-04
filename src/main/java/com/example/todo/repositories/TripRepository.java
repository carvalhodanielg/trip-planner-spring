package com.example.todo.repositories;

import com.example.todo.dto.response.TripResponseDTO;
import com.example.todo.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT t FROM Trip t LEFT JOIN t.transportations")
    List<Trip> findAllWithTransportations();
}
