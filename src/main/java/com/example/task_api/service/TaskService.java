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


    /**
     * 全タスク取得
     * @return　全タスク
     */
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

    /**
     * 特定のタスクの更新
     * @param request
     */
    public  void createTask(TaskCreateRequest request)  {

        Task task = new Task();
        task.setTitle(request.getTitle());

        repository.save(task);
        
    }

  
    /**
     * 特定のタスクを取得
     * @param id
     * @return idで指定されたタスク
     */
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

    public  void deleteTask(Integer id) {
        int result = repository.deleteTask(id);

        if(result ==0) {
            throw new TaskNotFoundException("削除対象のタスクがありません");
        }
    }
}
