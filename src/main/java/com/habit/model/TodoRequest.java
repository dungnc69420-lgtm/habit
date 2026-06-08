package com.habit.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private boolean completed = false;

    private Todo.Priority priority = Todo.Priority.MEDIUM;
}
