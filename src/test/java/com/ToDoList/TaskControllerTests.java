package com.ToDoList;

import com.ToDoList.controller.TaskController;
import com.ToDoList.dto.Task;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.service.TaskService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTests {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {

    }


    // Test get all tasks
    @Test
    public void testGetAllTasks() throws Exception {


        Task task1 = new Task();
        task1.setId(1L);
        task1.setContent("Task 1");
        task1.getCreationDate();

        Task task2 = new Task();
        task2.setId(2L);
        task2.setContent("Task 2");
        task2.getCreationDate();

        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskService.getTasks()).thenReturn(tasks);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("Home"))
                .andExpect(model().attribute("tasks", tasks))
            .andExpect(status().is2xxSuccessful());

    }

    //Test get one task
    @Test
    public void testGetTask() throws Exception {
        Long taskId = 1L;
        Task expectedTask = new Task();
        expectedTask.setId(taskId);
        expectedTask.setContent("Sample Content");

        when(taskService.getTask(taskId)).thenReturn(expectedTask);

        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        mockMvc.perform(get("/getTask/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testShowEditForm() {

        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        when(taskService.getTaskById(taskId)).thenReturn(task);

        String viewName = taskController.showEditForm(taskId, model);

        assertEquals("ModifyTask", viewName);

        verify(model, times(1)).addAttribute(eq("task"), any(Task.class));

        verify(taskService, times(1)).getTaskById(eq(taskId));
    }



    // Test save task
    @Test
    public void testSaveTask() throws Exception {
        Task task = new Task();

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .flashAttr("tasks", task))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }


    // Test update task
    @Test
    public void testUpdateTask() throws Exception {
        Long taskId = 1L;
        Task existingTask = new Task();

        Task taskDetails = new Task();
        taskDetails.setId(taskId);
        taskDetails.setContent("Example Content");

        String taskDetailsJson = new Gson().toJson(taskDetails);
        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(taskDetails);

        mockMvc.perform(MockMvcRequestBuilders.put("/updateTask/" + taskId)
                        .contentType("application/json")
                        .content(taskDetailsJson))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }


    // Test delete task
    @Test
    public void testDeleteTask() throws Exception {
        Long taskId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/" + taskId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
