package com.pus.companymanager.dto.project;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskDTO {

    private String name;
    private String description;
    private Integer priority;
    private LocalDateTime startDate;
    private LocalDateTime estimatedCompletionDate;
}
