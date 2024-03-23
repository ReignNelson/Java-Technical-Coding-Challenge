package com.challenge.taskmanager.controller;

import com.challenge.taskmanager.entity.Task;
import com.challenge.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return getTasks(model, "","name", "asc");
    }

    @GetMapping("/tasks")
    public String getTasks(Model model, String keyword, @RequestParam(required = false) String sortField, @RequestParam(required = false) String sortDir) {
        if(!Objects.equals(keyword, "")){
            model.addAttribute("tasks", taskService.getTaskByKeyword(keyword));

        }else{
            model.addAttribute("tasks", taskService.getAllTasks(sortField, sortDir));
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
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
    public String saveTaskForm(Model model, @ModelAttribute("task") Task task) {

         taskService.saveTask(task);
         return getTasks(model, "","name", "asc");

    }

    @GetMapping("/tasks/complete/{id}")
    public String updateTaskComplete(Model model, @PathVariable Long id,@ModelAttribute("task") Task task) {
        Task taskResult = taskService.getTaskById(id);
        taskResult.setComplete(!task.getComplete());
        taskService.saveTask(taskResult);

    return getTasks(model, "", "name", "asc");

    }
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(Model model, @PathVariable Long id){
        taskService.deleteTask(id);
        return getTasks(model, "", "name", "asc");
    }
}

