package com.example.taskManager.taskManager.service;


import com.example.taskManager.taskManager.dto.TaskDTO;
import com.example.taskManager.taskManager.entity.Task;
import com.example.taskManager.taskManager.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return taskRepository.findAll()
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

    //Delete a task
    public TaskDTO deleteTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Task not found"));
        taskRepository.delete(task);
        return modelMapper.map(task,TaskDTO.class);
    }

}
