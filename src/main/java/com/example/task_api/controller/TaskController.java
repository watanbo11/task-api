package com.example.task_api.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_api.response.TaskResponse;

@RestController 
@RequestMapping("/api/tasks")
public class TaskController {

    @GetMapping ("/{id}")
    public TaskResponse tasks(@PathVariable Integer id) {
        TaskResponse response = new TaskResponse();
        response.setId(id);
        response.setTitle("sample task");
        
        return  response;
    }
}
