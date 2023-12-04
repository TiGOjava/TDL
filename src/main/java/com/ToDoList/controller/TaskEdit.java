package com.ToDoList.controller;

import com.ToDoList.dto.Task;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskEdit {

    @Autowired
    private TaskService taskService;

    //Edit Task Form
    @RequestMapping("/editTask/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "ModifyTask";
    }
}
