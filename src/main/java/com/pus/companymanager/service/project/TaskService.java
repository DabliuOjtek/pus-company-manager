package com.pus.companymanager.service.project;

import com.pus.companymanager.repository.project.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
}
