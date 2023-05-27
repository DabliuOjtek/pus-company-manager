package com.pus.companymanager.controller.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {

    @GetMapping
    public List<ProjectDTO> getProjects(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ArrayList<>();
    }

    @GetMapping(name = "/{projectId}")
    public ProjectDTO getProject(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ProjectDTO();
    }

    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectDTO newProject, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ProjectDTO();
    }

    @PutMapping(name = "/{projectId}")
    public ProjectDTO updateProject(@PathVariable Long projectId, @RequestBody ProjectDTO projectToUpdate, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ProjectDTO();
    }

    @DeleteMapping(name = "/{projectId}")
    public void deleteProject(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

    }
}
