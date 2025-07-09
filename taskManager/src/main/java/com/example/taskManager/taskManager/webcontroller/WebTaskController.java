package com.example.taskManager.taskManager.webcontroller;


import com.example.taskManager.taskManager.dto.TaskDTO;
import com.example.taskManager.taskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WebTaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/web/tasks")
    public String viewTasks(Model model){
        List<TaskDTO> taskList = taskService.getAllTask();
        model.addAttribute("tasks",taskList);
        model.addAttribute("taskForm", new TaskDTO());
        return "tasks";
    }

    @PostMapping("/web/tasks")
    public String createTasks(Model model, @ModelAttribute("taskForm") @Valid TaskDTO taskDTO , BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("tasks",taskService.getAllTask());
            return "tasks";
        }
        taskService.createTask(taskDTO);
        return "redirect:/web/tasks";
    }

}
