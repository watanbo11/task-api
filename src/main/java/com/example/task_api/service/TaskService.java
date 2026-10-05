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
        
        TaskResponse response1 = new TaskResponse();
        response1.setId(task.size()+1);
        response1.setTitle("Javaを勉強する");

        TaskResponse response2 = new TaskResponse();
        response2.setId(task.size()+1);
        response2.setTitle("Spring Bootを勉強する");

        TaskResponse response3 = new TaskResponse();
        response3.setId(task.size()+1);
        response3.setTitle("Gitを勉強する");

        task.add(response1);
        task.add(response2);
        task.add(response3);

        return  task;
    }
}
