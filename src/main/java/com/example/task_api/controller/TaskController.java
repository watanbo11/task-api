package com.example.task_api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/hello")
public class TaskController {

    @GetMapping
    public Map<String,Object> hello() {
        Map<String,Object> message = Map.of("message","Hello REST API");
        return  message;
    }   

}
