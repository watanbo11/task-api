package com.example.task_api.service;

import org.springframework.stereotype.Service;

import com.example.task_api.request.TaskRequest;
import com.example.task_api.response.TaskResponse;

@Service 
public class TaskService {

    public  TaskResponse createTask(TaskRequest request)  {
        TaskResponse response = new TaskResponse();
        response.setId(1);
        response.setTitle(request.getTitle());
        return  response;
    }
}
