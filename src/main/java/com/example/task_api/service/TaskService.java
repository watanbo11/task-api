package com.example.task_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.task_api.entity.Task;
import com.example.task_api.exception.TaskNotFoundException;
import com.example.task_api.repository.TaskRepository;
import com.example.task_api.request.TaskCreateRequest;
import com.example.task_api.request.TaskRequest;
import com.example.task_api.request.TaskUpdateRequest;
import com.example.task_api.response.TaskResponse;

@Service 
public class TaskService {
    private  TaskRepository repository;
    
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    List<TaskResponse> tasks = new ArrayList<>();

    public List<TaskResponse> getTasks() {
        List<Task> tasks = repository.findAll();

        List<TaskResponse> list = new ArrayList<>();
        for(Task task : tasks) {
            TaskResponse response = new TaskResponse();
            response.setId(task.getId());
            response.setTitle(task.getTitle());
            list.add(response);
        }
        return  list;
    }

    public  void createTask(TaskCreateRequest request)  {

        Task task = new Task();
        task.setTitle(request.getTitle());

        repository.save(task);
        
    }

  

    public TaskResponse getTaskById(Integer id) {
        for(TaskResponse response : tasks) {
            if(response.getId().equals(id)) {
                return response;
            }
            
        }
        throw new TaskNotFoundException("task not found");
    }

    public  TaskResponse updateTask(Integer id , TaskUpdateRequest request) {
        for(TaskResponse task : tasks) {
            if(id.equals(task.getId())) {
                task.setTitle(request.getTitle());
                return  task;
            }
        }
         throw new TaskNotFoundException("更新対象のタスクが見つかりません");
    }
}
