package com.ToDoList.controller;

import com.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskDelete{


    @Autowired
    private TaskService taskService;


    //Delete Task
    @RequestMapping("/tasks/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}









