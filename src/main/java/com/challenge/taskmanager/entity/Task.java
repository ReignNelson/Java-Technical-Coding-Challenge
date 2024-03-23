package com.challenge.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private Boolean complete = false;

    public Task(){

    }
    public Task(Long id, String name, String description, LocalDate dueDate, Boolean complete) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.complete = complete;
    }
    public Task(String name, String description, LocalDate dueDate, Boolean complete) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.complete = complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

}

