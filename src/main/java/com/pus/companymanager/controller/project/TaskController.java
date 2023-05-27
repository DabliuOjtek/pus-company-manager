package com.pus.companymanager.controller.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.ProjectDTO;
import com.pus.companymanager.dto.project.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects/{projectId}/tasks")
public class TaskController {

    @GetMapping
    public List<ProjectDTO> getTasks(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ArrayList<>();
    }

    @GetMapping(name = "/{taskId}")
    public TaskDTO getTask(@PathVariable Long projectId, @PathVariable Long taskId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new TaskDTO();
    }

    @PostMapping
    public TaskDTO createTask(@PathVariable Long projectId, @RequestBody TaskDTO newTask, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new TaskDTO();
    }

    @PutMapping(name = "/{taskId}")
    public TaskDTO updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskDTO taskToUpdate, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new TaskDTO();
    }

    @DeleteMapping(name = "/{taskId}")
    public void deleteTask(@PathVariable Long projectId, @PathVariable Long taskId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

    }
}
