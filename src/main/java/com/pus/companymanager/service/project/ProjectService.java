package com.pus.companymanager.service.project;

import com.pus.companymanager.repository.project.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;
}
