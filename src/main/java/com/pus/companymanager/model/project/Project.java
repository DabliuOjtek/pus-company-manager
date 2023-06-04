package com.pus.companymanager.model.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime completionDate;

    @OneToMany(mappedBy = "id", orphanRemoval = true)
    private List<Member> members;

    @OneToMany(mappedBy = "id", orphanRemoval = true)
    private List<Task> tasks;
}
