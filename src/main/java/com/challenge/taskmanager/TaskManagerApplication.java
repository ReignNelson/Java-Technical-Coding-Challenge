package com.challenge.taskmanager;

import com.challenge.taskmanager.entity.Task;
import com.challenge.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@SpringBootApplication
@RestController
public class TaskManagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Autowired
	private TaskRepository taskRepository;
	@Override
	public void run(String... args) throws Exception {
		Task task1 = new Task("Sleep", "Need to catch up on sleep", LocalDate.of(2010,1,2),false);
		Task task2 = new Task("Walk Dog in morning", "Im watching my friends dog and need to walk her twice a day", LocalDate.of(2020,5,2),false);
		Task task3 = new Task("Do taxes", "Ive been procrastinating", LocalDate.of(2022,3,2),false);
		Task task4 = new Task("Meal prep", "Ive been craving chicken adobo", LocalDate.of(2090,5,12),false);

		taskRepository.save(task1);
		taskRepository.save(task2);
		taskRepository.save(task3);
		taskRepository.save(task4);


	}
}
