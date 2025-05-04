package com.example.todo.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class TransportationResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Instant date;
    private Long tripId;
}
