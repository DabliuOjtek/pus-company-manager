package com.pus.companymanager.controller.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.TaskDTO;
import com.pus.companymanager.service.project.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDTO> getTasks(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return taskService.getTasks(projectId, userDetails);
    }

    @GetMapping("/{taskId}")
    public TaskDTO getTask(@PathVariable Long projectId, @PathVariable Long taskId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return taskService.getTaskById(projectId, taskId, userDetails);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@PathVariable Long projectId, @RequestBody TaskDTO newTask, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TaskDTO task = taskService.createTask(projectId, newTask, userDetails);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public TaskDTO updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskDTO taskToUpdate, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return null;
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long projectId, @PathVariable Long taskId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

    }
}
