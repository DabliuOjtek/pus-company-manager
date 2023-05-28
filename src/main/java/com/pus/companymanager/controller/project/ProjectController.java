package com.pus.companymanager.controller.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.ProjectDTO;
import com.pus.companymanager.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> getProjects(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return projectService.getAllUserProject(userDetails);
    }

    @GetMapping(name = "/{projectId}")
    public ProjectDTO getProject(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return projectService.getProjectDetails(projectId, userDetails);
    }

    @PostMapping
    public ResponseEntity<Long> createProject(@RequestBody ProjectDTO newProject, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long id = projectService.createProject(newProject, userDetails);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(name = "/{projectId}")
    public Long updateProject(@PathVariable Long projectId, @RequestBody ProjectDTO projectToUpdate, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return projectService.updateProject(projectId, projectToUpdate, userDetails);
    }

    @DeleteMapping(name = "/{projectId}")
    public void deleteProject(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectService.deleteProject(projectId, userDetails);
    }
}
