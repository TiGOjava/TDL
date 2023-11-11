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
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;


    //Get All Tasks
    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("Task", new Task());
        return "Home";
    }

    //Get One Task
    @SneakyThrows
    @GetMapping(value = "/getTask/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }


    //Add Task
    @PostMapping("/tasks")
    public String saveTask(@ModelAttribute("tasks") Task task, Model model) {
        taskService.saveTask(task);;
        model.addAttribute("task", task);
        return "redirect:/tasks";
    }

    //Edit Task Form
    @RequestMapping("/editTask/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "ModifyTask";
    }

    //Update Tasks
    @PutMapping("/updateTask/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updateTask = taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task not exist with id: " + id));

        updateTask.setContent(taskDetails.getContent());
        taskRepository.save(updateTask);
        return "redirect:/tasks";
    }

    //Delete Task
    @RequestMapping("/tasks/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}









