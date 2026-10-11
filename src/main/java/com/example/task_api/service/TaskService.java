package com.example.task_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.task_api.entity.Task;
import com.example.task_api.exception.TaskNotFoundException;
import com.example.task_api.repository.TaskRepository;
import com.example.task_api.request.TaskCreateRequest;
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

    //タスク登録
    public  void createTask(TaskCreateRequest request)  {

        Task task = new Task();
        task.setTitle(request.getTitle());

        repository.save(task);
        
    }

  
    //特定のタスクを指定して取得
    public TaskResponse getTaskById(Integer id) {
       Optional<Task> optionalTask = repository.findById(id);
       Task task =optionalTask.orElseThrow(() -> new TaskNotFoundException("タスクが見つかりません"));
       TaskResponse response = new TaskResponse();
       response.setId(task.getId());
       response.setTitle(task.getTitle());
       return  response;
    }

    /**
     * 更新タスク
     * @param id
     * @param request
     * @return 更新対象のタスク
     */
    public  void updateTask(Integer id , TaskUpdateRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());

        int result = repository.updateTask(task, id);
        if(result ==0) {
            throw new TaskNotFoundException("更新対象のタスクがありません");
        }
      
    }
}
