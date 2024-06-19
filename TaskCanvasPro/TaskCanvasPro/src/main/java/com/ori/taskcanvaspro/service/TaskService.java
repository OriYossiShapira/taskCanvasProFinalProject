package com.ori.taskcanvaspro.service;

import com.ori.taskcanvaspro.dto.TaskCreateDTO;
import com.ori.taskcanvaspro.dto.TaskListDTO;
import com.ori.taskcanvaspro.dto.TaskResponseDTO;

public interface TaskService {
   TaskResponseDTO createTask(TaskCreateDTO dto);
   TaskListDTO getAllTasks();
   TaskListDTO getAllTasks(long userId);
   TaskResponseDTO updateTask(TaskResponseDTO dto);
   boolean deleteTask(Long id);
   TaskResponseDTO getTask(Long id);

}