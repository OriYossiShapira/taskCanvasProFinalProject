package com.ori.taskcanvaspro.dto;

import com.ori.taskcanvaspro.model.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDTO {

    private String firstName;
    private String lastName;

    private String email;

    private String password;
    private String team;
    private boolean isAdmin;


    Set<Task> tasks;
}
