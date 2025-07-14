package com.example.taskManager.taskManager.restcontroller;


import com.example.taskManager.taskManager.dto.TaskDTO;
import com.example.taskManager.taskManager.service.TaskService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    //to filter with status
    @GetMapping("status/{status}")
    public ResponseEntity<List<TaskDTO>> getTasksByStatus(@PathVariable String status){
        List<TaskDTO> taskDTO = taskService.getTasksByStatus(status);
        return ResponseEntity.status(200).body(taskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id){
        TaskDTO deletedTask = taskService.deleteTask(id);
        return ResponseEntity.status(200).body(deletedTask);
    }

    //update task with id
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO updatedTask){
        TaskDTO updated = taskService.updateTask(id,updatedTask);
        return ResponseEntity.status(200).body(updated);
    }

    //update only status of a task
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDTO> updateStatusOfTask(@PathVariable Long id, @RequestBody Map<String,String> requestBody){
        String status = requestBody.get("status");
        TaskDTO updated = taskService.updateStatusOfTask(id,status);
        return ResponseEntity.status(200).body(updated);
    }
}
