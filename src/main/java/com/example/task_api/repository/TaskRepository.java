package com.example.task_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.task_api.entity.Task;
import com.example.task_api.request.TaskUpdateRequest;

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
    //タスク登録
    public int save(Task task) {
        String sql = "INSERT INTO tasks (title) VALUES(?)";
        return  template.update(sql,task.getTitle());
    }

   //特定のタスク取得
   public Optional<Task> findById(Integer id) {
    TaskRowMapper mapper = new TaskRowMapper();
    String sql = "SELECT * FROM tasks WHERE id = ?";

    List<Task> tasks =template.query(sql, mapper,id);

    Optional<Task> task = tasks.stream().findFirst();

    return  task;
   }  

   //タスク更新
   public int updateTask(Task task,Integer id) {
    String sql = "UPDATE tasks SET title = ? WHERE id =?";
    int count = template.update(sql,task.getTitle(),id);
    return  count;
   }


   
}
