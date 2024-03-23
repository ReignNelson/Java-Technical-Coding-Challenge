package com.challenge.taskmanager.service.TaskServiceImpl;

import com.challenge.taskmanager.entity.Task;
import com.challenge.taskmanager.repository.TaskRepository;
import com.challenge.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTaskByKeyword(String keyword) {
        if (keyword != null) {
            return taskRepository.findByKeyword(keyword);
        }
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> allTask = taskRepository.findAll();
        allTask.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.getDueDate().isBefore(t2.getDueDate())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return allTask;
    }


    public Task getTaskById(Long id){
        if (taskRepository.findById(id).isPresent()){
            return taskRepository.findById(id).get();
        }
        return null;
    }

}
