package com.ToDoList.controller;

import com.ToDoList.dto.Task;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.service.TaskService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskGetOne {


    @Autowired
    private TaskService taskService;

    //Get One Task
    @SneakyThrows
    @GetMapping(value = "/getTask/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }



}
