package com.example.todo.entities;


import com.example.todo.enums.TripStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trips")
public class Trip extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String destination;

    @Column
    private Instant startDate;

    @Column
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    @Column
    private TripStatus status = TripStatus.PLANNING;

    @Column
    private Boolean isPrivate = true;

    @Column(precision = 12, scale = 2)
    private BigDecimal budget = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal spending = BigDecimal.ZERO;

    @Column
    private String coverImageUrl;

    @Column
    private String review;

    @Column()
    @Min(1)
    @Max(5)
    private Integer rating = 1;

}
