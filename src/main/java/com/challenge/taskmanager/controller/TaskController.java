package com.challenge.taskmanager.controller;

import com.challenge.taskmanager.entity.Task;
import com.challenge.taskmanager.service.TaskService;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model, String keyword) {

        if(!Objects.equals(keyword, "")){
            model.addAttribute("tasks", taskService.getTaskByKeyword(keyword));

        }else{
            model.addAttribute("tasks", taskService.getAllTasks());
        }
        return "tasks";
    }
    @GetMapping("/tasks/new")
    public String createTaskForm(Model model) {

        Task task = new Task();
        model.addAttribute("tasks", task);
        return "create_task";

    }
    @PostMapping("/tasks")
    public String saveTaskForm(@ModelAttribute("task") Task task) {

         taskService.saveTask(task);
         return "redirect:/tasks";

    }

    @GetMapping("/tasks/complete/{id}")
    public String updateTaskComplete(@PathVariable Long id,@ModelAttribute("task") Task task) {
        Task taskResult = taskService.getTaskById(id);
        taskResult.setComplete(!task.getComplete());
        taskService.saveTask(taskResult);

    return "redirect:/tasks";

    }
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
