package com.ToDoList.controller;

import com.ToDoList.dto.Task;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TaskGetAll {


    @Autowired
    private TaskService taskService;

    //Get All Tasks
    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("Task", new Task());
        return "Home";
    }
}
