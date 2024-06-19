package com.ori.taskcanvaspro.model.entity;


import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private LocalDate deadline;

    private boolean completed;

    private Integer progress;

    @ManyToOne
    @JsonIncludeProperties({"email"})
    private User user;
    @OneToMany
    Set<SubTask> subTasks;
}

