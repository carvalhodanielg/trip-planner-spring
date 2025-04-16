package com.example.todo.dto.request;

import com.example.todo.entities.Trip;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransportationRequestDTO {
    private String name;

    private String description;

    private BigDecimal price;

    private Instant date;

    private Long tripId;


}
