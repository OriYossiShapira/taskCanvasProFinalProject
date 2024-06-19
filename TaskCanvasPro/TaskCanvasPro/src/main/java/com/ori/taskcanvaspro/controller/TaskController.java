package com.ori.taskcanvaspro.controller;

import com.ori.taskcanvaspro.dto.TaskCreateDTO;
import com.ori.taskcanvaspro.dto.TaskListDTO;
import com.ori.taskcanvaspro.dto.TaskResponseDTO;
import com.ori.taskcanvaspro.dto.UserResponseDTO;
import com.ori.taskcanvaspro.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<TaskListDTO> getAllTasks() {
        TaskListDTO responseDTO = taskService.getAllTasks();
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createPost(@RequestBody TaskCreateDTO dto, UriComponentsBuilder uriBuilder) {
        var res = taskService.createTask(dto);

        var uri = uriBuilder.path("/api/v1/tasks/:id").buildAndExpand(res.getId()).toUri();
        return ResponseEntity.created(uri).body(res);
    }

    @PutMapping
    public ResponseEntity<TaskResponseDTO> UpdateTask(@RequestBody TaskResponseDTO dto, UriComponentsBuilder uriBuilder) {
        var res = taskService.updateTask(dto);

        var uri = uriBuilder.path("/api/v1/tasks/:id").buildAndExpand(res.getId()).toUri();
        return ResponseEntity.created(uri).body(res);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> getUser(@PathVariable long taskId) {
        TaskResponseDTO task = taskService.getTask(taskId);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/user/{userId}") // http://localhost:8080/api/v1/users/1589 -> userId = 1589
    public ResponseEntity<TaskListDTO> getUserTasks(@PathVariable long userId){
        TaskListDTO dto = taskService.getAllTasks(userId);
        return ResponseEntity.ok(dto);
    }
}