package com.ori.taskcanvaspro.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskListDTO {
    private long totalTasks;
    private int pageNo;
    private int PageSize;
    private int totalPages;

    private boolean isFirst;
    private boolean isLast;
    private Collection<TaskResponseDTO> tasks;
}
