package com.example.task_api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_api.request.TaskRequest;
import com.example.task_api.request.TaskUpdateRequest;
import com.example.task_api.response.TaskDeleteResponse;
import com.example.task_api.response.TaskResponse;
import com.example.task_api.service.TaskService;

@RestController 
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService service;
    

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping ("/{id}")
    public TaskResponse tasks(@PathVariable Integer id) {
        TaskResponse response = new TaskResponse();
        response.setId(id);
        response.setTitle("sample task");
        
        return  response;
    }

    @GetMapping 
    public List<TaskResponse> getTasks() {
        List<TaskResponse> tasks = new ArrayList<>();
        TaskResponse response1 = new TaskResponse();
        response1.setId(1);
        response1.setTitle("Javaを勉強する");

        TaskResponse response2 = new TaskResponse();
        response2.setId(2);
        response2.setTitle("Spring Bootを勉強する");

        TaskResponse response3 = new TaskResponse();
        response3.setId(3);
        response3.setTitle("Gitを勉強する");

        tasks.add(response1);
        tasks.add(response2);
        tasks.add(response3);

        return tasks;
    }

    @PostMapping
    public TaskResponse regist(@RequestBody  TaskRequest request) {

        TaskResponse response = service.createTask(request);
        return  response;
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Integer id ,@RequestBody TaskUpdateRequest request) {    

        TaskResponse response = new TaskResponse();
        response.setId(id);
        response.setTitle(request.getTitle());
        return  response;
        
    }

    @DeleteMapping("/{id}")
    public  TaskDeleteResponse deleteTask(@PathVariable Integer id) {

        TaskDeleteResponse response = new TaskDeleteResponse();
        response.setId(id);
        response.setMessage("Task deleted");

        return  response;
    }
    }
