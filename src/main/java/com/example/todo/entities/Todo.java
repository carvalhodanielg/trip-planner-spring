package com.example.todo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "todo")
public class Todo extends BaseEntity{

    @Schema(description = "Task title", required = true, example = "Buy rice")
    @NotBlank(message = "Title is required.")
    @Size(min =3, max = 100, message = "Must have at least 100 characters.")
    @Column(nullable = false, length = 100)
    private String title;

    @NotNull(message = "Description is required.")
    @Size(max = 500, message = "Must have at least 500 characters.")
    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private boolean completed;

    public Todo() {}

    public Todo(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }
}
