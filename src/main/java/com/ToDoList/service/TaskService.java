package com.ToDoList.service;

import com.ToDoList.dto.Task;
import com.ToDoList.exception.TaskNotFoundException;
import com.ToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public void saveTask(Task task){ taskRepository.save(task);}

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        if(!optionalTask.isPresent())
            throw new TaskNotFoundException("Task Record is not available...");

        return optionalTask.get();
    }
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void deleteTask(Long id) { taskRepository.deleteById(id);}


    }

