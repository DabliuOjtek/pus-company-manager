package com.pus.companymanager.dto.project;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime completionDate;
}
