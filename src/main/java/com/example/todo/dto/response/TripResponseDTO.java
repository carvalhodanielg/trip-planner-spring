package com.example.todo.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class TripResponseDTO {
    private String name;
    private String description;
    private String destination;
    private Instant startDate;
    private Instant endDate;
    private String status;
    private Boolean isPrivate;
    private BigDecimal budget;
    private BigDecimal spending;
    private String coverImageUrl;
    private String review;
    private Integer rating;
}
