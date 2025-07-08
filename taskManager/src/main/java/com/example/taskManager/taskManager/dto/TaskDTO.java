package com.example.taskManager.taskManager.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskDTO {

    @NotNull(message="Title must not be null")
    @NotBlank(message="Title must not be blank")
    @Size(max=100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message="Description must not be blank")
    private String description;
    @NotBlank(message="Status must not be blank")
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
