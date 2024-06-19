package com.ori.taskcanvaspro.dto;

import com.ori.taskcanvaspro.model.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDTO {
    private long id;

    private String title;

    private String description;

    private LocalDate deadline;

    private boolean completed;

    private Integer progress;
    private User user;
    private Set<SubTaskDTO> subTasks;
}
