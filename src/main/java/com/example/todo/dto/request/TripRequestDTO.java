package com.example.todo.dto.request;

import com.example.todo.enums.TripStatus;
import com.example.todo.infra.EnumValidator;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TripRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;
    private String destination;
    private Instant startDate;
    private Instant endDate;

    @EnumValidator(enumClass = TripStatus.class)
    private String status;

    private Boolean isPrivate;
    private BigDecimal budget;
    private BigDecimal spending;
    private String coverImageUrl;
    private String review;
    private Integer rating;

}
