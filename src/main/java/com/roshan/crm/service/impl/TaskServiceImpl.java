package com.roshan.crm.service.impl;

import com.roshan.crm.entity.Deal;
import com.roshan.crm.entity.Lead;
import com.roshan.crm.entity.Task;
import com.roshan.crm.entity.User;
import com.roshan.crm.exception.ResourceNotFoundException;
import com.roshan.crm.repository.DealRepository;
import com.roshan.crm.repository.LeadRepository;
import com.roshan.crm.repository.TaskRepository;
import com.roshan.crm.repository.UserRepository;
import com.roshan.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private DealRepository dealRepository;

    @Override
    public Task createTask(Task task) {
        if (task.getAssignedTo() != null && task.getAssignedTo().getId() != null) {
            User assignedTo = userRepository.findById(task.getAssignedTo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
            task.setAssignedTo(assignedTo);
        }
        if (task.getLead() != null && task.getLead().getId() != null) {
            Lead lead = leadRepository.findById(task.getLead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
            task.setLead(lead);
        }
        if (task.getDeal() != null && task.getDeal().getId() != null) {
            Deal deal = dealRepository.findById(task.getDeal().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Deal not found"));
            task.setDeal(deal);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setDueDate(taskDetails.getDueDate());
        existingTask.setStatus(taskDetails.getStatus());

        if (taskDetails.getAssignedTo() != null && taskDetails.getAssignedTo().getId() != null) {
            User assignedTo = userRepository.findById(taskDetails.getAssignedTo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
            existingTask.setAssignedTo(assignedTo);
        } else {
            existingTask.setAssignedTo(null);
        }

        if (taskDetails.getLead() != null && taskDetails.getLead().getId() != null) {
            Lead lead = leadRepository.findById(taskDetails.getLead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
            existingTask.setLead(lead);
        } else {
            existingTask.setLead(null);
        }

        if (taskDetails.getDeal() != null && taskDetails.getDeal().getId() != null) {
            Deal deal = dealRepository.findById(taskDetails.getDeal().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Deal not found"));
            existingTask.setDeal(deal);
        } else {
            existingTask.setDeal(null);
        }

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByAssignedToId(userId);
    }
}
