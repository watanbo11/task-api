package com.example.task_api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.task_api.entity.Task;
import com.example.task_api.response.TaskResponse;
import com.example.task_api.service.TaskService;

@Repository 
public class TaskRepository  {

    private JdbcTemplate template;


    public TaskRepository(JdbcTemplate template) {
        this.template = template;
      
    }

    
    //全件タスク取得
    public List<Task> findAll() {
        TaskRowMapper mapper = new TaskRowMapper();
        String sql = "SELECT * FROM tasks";
        return template.query(sql, mapper);

    }

    public int save(Task task) {
        String sql = "INSERT INTO tasks (title) VALUES(?)";
        return  template.update(sql,task.getTitle());
    }



   
}
