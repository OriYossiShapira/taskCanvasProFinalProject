package com.ori.taskcanvaspro.model.repository;

import com.ori.taskcanvaspro.model.entity.Task;
import com.ori.taskcanvaspro.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);
}
