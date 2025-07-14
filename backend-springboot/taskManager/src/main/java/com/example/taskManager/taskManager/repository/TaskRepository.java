package com.example.taskManager.taskManager.repository;

import com.example.taskManager.taskManager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByStatusIgnoreCase(String status);

}
