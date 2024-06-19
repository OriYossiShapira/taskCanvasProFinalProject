package com.ori.taskcanvaspro.service;

import com.ori.taskcanvaspro.dto.TaskCreateDTO;
import com.ori.taskcanvaspro.dto.TaskListDTO;
import com.ori.taskcanvaspro.dto.TaskResponseDTO;
import com.ori.taskcanvaspro.exeption.ResourceNotFoundException;
import com.ori.taskcanvaspro.model.entity.SubTask;
import com.ori.taskcanvaspro.model.entity.Task;
import com.ori.taskcanvaspro.model.entity.User;
import com.ori.taskcanvaspro.model.repository.SubTaskRepository;
import com.ori.taskcanvaspro.model.repository.TaskRepository;
import com.ori.taskcanvaspro.model.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final SubTaskRepository subTaskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public TaskResponseDTO createTask(TaskCreateDTO dto) {
        Task task = modelMapper.map(dto, Task.class);

        var saved = taskRepository.save(task);

        User user = userRepository.findById(saved.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("invalid id " + saved.getUser())) ;
        user.getTasks().add(saved);
        userRepository.save(user);
        return modelMapper.map(saved, TaskResponseDTO.class);
    }

    @Override
    public TaskResponseDTO updateTask(TaskResponseDTO dto) {
        Task task = modelMapper.map(dto, Task.class);
        // TODO if task does not exists throw exception
        boolean taskExists = taskRepository.existsById(task.getId());
        if(!taskExists){
            throw new IllegalArgumentException("invalid task");
        }
        for(SubTask st :task.getSubTasks()){
            subTaskRepository.save(st);
        }
        var saved = taskRepository.save(task);

        return modelMapper.map(saved, TaskResponseDTO.class);
    }

    @Override
    public boolean deleteTask(Long id){
        boolean taskExists = taskRepository.existsById(id);
        // TODO if task does not exists throw exception
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public TaskResponseDTO getTask(Long id) {
        Optional<Task> result = taskRepository.findById(id);
        if(!result.isEmpty()){
            Task task = result.get();
            return modelMapper.map(task, TaskResponseDTO.class);
        }
        return null;
    }

    @Override
    public TaskListDTO getAllTasks() {
        Collection<TaskResponseDTO> dtoList = taskRepository.findAll()
                .stream()
                .map( t ->modelMapper.map(t, TaskResponseDTO.class))
                .collect(Collectors.toList());
        TaskListDTO taskListDTO = new TaskListDTO();
        taskListDTO.setTotalTasks(dtoList.size());
        taskListDTO.setTasks(dtoList);
        return taskListDTO;
    }

    public TaskListDTO getAllTasks(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("invalid id " + userId)) ;

        Collection<TaskResponseDTO> dtoList = taskRepository.findByUser(user)
                .stream()
                .map( t ->modelMapper.map(t, TaskResponseDTO.class))
                .collect(Collectors.toList());
        TaskListDTO taskListDTO = new TaskListDTO();
        taskListDTO.setTotalTasks(dtoList.size());
        taskListDTO.setTasks(dtoList);
        return taskListDTO;
    }


}
