package com.challenge.taskmanager.service.TaskServiceImpl;

import com.challenge.taskmanager.entity.Task;
import com.challenge.taskmanager.repository.TaskRepository;
import com.challenge.taskmanager.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        super();
        this.taskRepository = taskRepository;
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTaskByKeyword(String keyword) {
        if (keyword != null){
            return taskRepository.findByKeyword(keyword);
        }
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasks(String sortField, String sortDir) {
        if(sortField != null && sortDir != null){
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
            return taskRepository.findAll(sort);
        }else{
            Sort sort = Sort.by("name").ascending();
            return taskRepository.findAll(sort);
        }

    }

    public Task getTaskById(Long id){
        if (taskRepository.findById(id).isPresent()){
            return taskRepository.findById(id).get();
        }
        return null;
    }

}
