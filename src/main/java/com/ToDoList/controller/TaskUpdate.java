package com.ToDoList.controller;

import com.ToDoList.dto.Task;
import com.ToDoList.exception.TaskNotFoundException;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TaskUpdate {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    //Update Tasks
    @PutMapping("/updateTask/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updateTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not exist with id: " + id));

        updateTask.setContent(taskDetails.getContent());
        taskRepository.save(updateTask);
        return "redirect:/ViewEditTask";
    }


}
