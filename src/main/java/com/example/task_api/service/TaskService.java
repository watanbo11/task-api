package com.example.task_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.task_api.exception.TaskNotFoundException;
import com.example.task_api.request.TaskRequest;
import com.example.task_api.response.TaskResponse;

@Service 
public class TaskService {
    List<TaskResponse> tasks = new ArrayList<>();

    public  TaskResponse createTask(TaskRequest request)  {
        TaskResponse response = new TaskResponse();
        response.setId(tasks.size()+1);
        response.setTitle(request.getTitle());
        tasks.add(response);
        return  response;
    }

    public  List<TaskResponse> getTasks() {
        
        return  tasks;
    }

    public TaskResponse getTaskById(Integer id) {
        for(TaskResponse response : tasks) {
            if(response.getId().equals(id)) {
                return response;
            }
            
        }
        throw new TaskNotFoundException("task not found");
    }
}
