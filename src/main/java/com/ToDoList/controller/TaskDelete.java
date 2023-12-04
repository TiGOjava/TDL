package com.ToDoList.controller;

import com.ToDoList.dto.Task;
import com.ToDoList.exception.TaskNotFoundException;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.service.TaskService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskDelete {


    @Autowired
    private TaskService taskService;


    //Delete Task
    @RequestMapping("/tasks/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}









