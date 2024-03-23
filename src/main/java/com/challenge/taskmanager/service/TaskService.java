package com.challenge.taskmanager.service;

import com.challenge.taskmanager.entity.Task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    void deleteTask(Long id);
    void saveTask(Task task);
    List<Task> getTaskByKeyword(String keyword);
}
