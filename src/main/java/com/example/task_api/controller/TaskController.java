package com.example.task_api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.task_api.request.TaskCreateRequest;
import com.example.task_api.request.TaskRequest;
import com.example.task_api.request.TaskUpdateRequest;
import com.example.task_api.response.TaskDeleteResponse;
import com.example.task_api.response.TaskResponse;
import com.example.task_api.service.TaskService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService service;
    

    public TaskController(TaskService service) {
        this.service = service;
    }

    /**
     * パラメータで送られてきたidのタスクを取得する
     * @param id
     * @return 引数で受け取ったidのタスク
     */
    @GetMapping ("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) {


        TaskResponse response = service.getTaskById(id);
        
        return  ResponseEntity.ok()
                              .body(response);
    }

    /**
     * 登録されているすべてのタスクを取得
     * @return 全タスク
     */
    @GetMapping 
    public ResponseEntity<List<TaskResponse>> getTasks() {
      
        List<TaskResponse> responses = service.getTasks();
        return ResponseEntity.ok()
                             .body(responses);
    }
    
    // タスク登録
    /**
     * 
     * @param request
     * @return 登録成功の場合、HTTPステータス201
     */
    @PostMapping
    public ResponseEntity<Void> createTask(@Valid @RequestBody  TaskCreateRequest request) {

        service.createTask(request);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 指定されたidのタスクを更新する
     * @param id
     * @param request
     * @return 更新対象のタスク
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask( @PathVariable Integer id ,@Valid @RequestBody TaskUpdateRequest request) {    

        service.updateTask(id,request);
        
        return  ResponseEntity.noContent().build();
        
    }

    @DeleteMapping("/{id}")
    public  TaskDeleteResponse deleteTask(@PathVariable Integer id) {

        TaskDeleteResponse response = new TaskDeleteResponse();
        response.setId(id);
        response.setMessage("Task deleted");

        return  response;
    }
    }
