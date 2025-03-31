package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.aspectj.bridge.IMessage;

@Data
public class TodoRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3-100 chars")
    private String title;

    @NotBlank(message  = "Description is required")
    @Size(max = 500, message = "Description must be <= 500 chars")
    private String description;

    private boolean completed = false;

}
