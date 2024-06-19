package com.ori.taskcanvaspro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubTaskDTO {
    private Long id;
    @NotNull
    @Size(min = 2, max = 150)
    private String title;

    @NotNull
    @Size(min = 2, max = 512)
    private String description;

    private boolean done;
}
