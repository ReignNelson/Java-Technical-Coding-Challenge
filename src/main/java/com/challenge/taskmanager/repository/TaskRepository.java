package com.challenge.taskmanager.repository;

import com.challenge.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.description like %?1%")
    List<Task> findByKeyword(@Param("keyword") String keyword);

}
