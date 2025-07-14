package com.example.taskManager.taskManager.service;


import com.example.taskManager.taskManager.dto.TaskDTO;
import com.example.taskManager.taskManager.entity.Task;
import com.example.taskManager.taskManager.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Create a Task
    public TaskDTO createTask(TaskDTO taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        Task saved = taskRepository.save(task);
        return modelMapper.map(saved, TaskDTO.class);
    }

    //Get all Tasks
    public List<TaskDTO> getAllTask(){
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(task-> modelMapper.map(task,TaskDTO.class))
                .collect(Collectors.toList());
    }

    //Get task by id
    public TaskDTO getTaskById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Task not found"));

        return modelMapper.map(task,TaskDTO.class);
    }

    //get task by status
    public List<TaskDTO> getTasksByStatus(String status) {
        //checks for null and blank values
        if (status == null || status.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is required");
        }

        //validates the legit status codes
        List<String> allowedStatuses = Arrays.asList("PENDING", "IN_PROGRESS", "DONE", "COMPLETED");
        if (!allowedStatuses.contains(status.toUpperCase())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value");
        }

        List<Task> filteredTasks = taskRepository.findByStatusIgnoreCase(status);

        return filteredTasks.stream()
                .map(tasks -> modelMapper.map(tasks,TaskDTO.class))
                .collect(Collectors.toList());
    }

    //Delete a task
    public TaskDTO deleteTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Task not found"));
        taskRepository.delete(task);
        return modelMapper.map(task,TaskDTO.class);
    }

    //update a task
    public TaskDTO updateTask(Long id,TaskDTO updatedTask){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found"));
        modelMapper.map(updatedTask,existingTask);

        Task saved = taskRepository.save(existingTask);
        return modelMapper.map(saved,TaskDTO.class);
    }

    public TaskDTO updateStatusOfTask(Long id, String toUpdateStatus) {
        //checks for null and blank values
        if (toUpdateStatus == null || toUpdateStatus.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is required");
        }

        //validates the legit status codes
        List<String> allowedStatuses = Arrays.asList("PENDING", "IN_PROGRESS", "DONE", "COMPLETED");
        if (!allowedStatuses.contains(toUpdateStatus.toUpperCase())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value");
        }

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found"));

        existingTask.setStatus(toUpdateStatus);
        Task savedTask = taskRepository.save(existingTask);
        return modelMapper.map(savedTask,TaskDTO.class);
    }
}
