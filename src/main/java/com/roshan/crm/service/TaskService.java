package com.roshan.crm.service;

import com.roshan.crm.entity.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    List<Task> getTasksByUserId(Long userId);
}
