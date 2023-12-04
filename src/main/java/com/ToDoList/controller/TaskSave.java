package com.ToDoList.controller;

import com.ToDoList.dto.Task;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskSave {

    @Autowired
    private TaskService taskService;

    //Add Task
    @PostMapping("/tasks")
    public String saveTask(@ModelAttribute("tasks") Task task, Model model) {
        taskService.saveTask(task);;
        model.addAttribute("task", task);
        return "redirect:/tasks";
    }

}
