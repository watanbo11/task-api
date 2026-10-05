package com.example.task_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.task_api.request.TaskRequest;
import com.example.task_api.response.TaskResponse;

@Service 
public class TaskService {
    List<TaskResponse> task = new ArrayList<>();

    public  TaskResponse createTask(TaskRequest request)  {
        TaskResponse response = new TaskResponse();
        response.setId(task.size()+1);
        response.setTitle(request.getTitle());
        task.add(response);
        return  response;
    }

    public  List<TaskResponse> getTasks() {
        
        return  task;
    }
}
