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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCreateDTO {
    @NotNull
    @Size(min = 2, max = 128)
    private String title;

    @NotNull
    @Size(min = 2, max = 512)
    private String description;

    @NotNull
    private LocalDate deadline;

    private boolean completed;

    @Min(0)
    @Max(100)
    private Integer progress;
    private UserResponseDTO user;
}
