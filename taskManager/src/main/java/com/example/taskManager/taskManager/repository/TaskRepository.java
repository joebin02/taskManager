package com.example.taskManager.taskManager.repository;

import com.example.taskManager.taskManager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
