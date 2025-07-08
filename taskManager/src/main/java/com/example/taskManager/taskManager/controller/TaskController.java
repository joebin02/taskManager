package com.example.taskManager.taskManager.controller;


import com.example.taskManager.taskManager.dto.TaskDTO;
import com.example.taskManager.taskManager.entity.Task;
import com.example.taskManager.taskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTasks(@Valid @RequestBody TaskDTO taskDto){
        TaskDTO created = taskService.createTask(taskDto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> allTasks = taskService.getAllTask();
        return ResponseEntity.status(200).body(allTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
        TaskDTO taskDTO = taskService.getTaskById(id);
        return ResponseEntity.ok(taskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id){
        TaskDTO deletedTask = taskService.deleteTask(id);
        return ResponseEntity.status(200).body(deletedTask);
    }
}
