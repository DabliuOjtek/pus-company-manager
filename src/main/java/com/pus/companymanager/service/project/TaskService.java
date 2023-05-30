package com.pus.companymanager.service.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.TaskDTO;
import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.project.Project;
import com.pus.companymanager.model.project.Task;
import com.pus.companymanager.repository.project.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
    private ProjectService projectService;
    private MemberService memberService;

    public List<TaskDTO> getTasks(Long projectId, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);

        return taskRepository.findAllByProjectId(projectId)
                .stream()
                .map(this::mapTaskToTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long projectId, Long taskId, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);

        return taskRepository.findById(taskId)
                .map(this::mapTaskToTaskDTO)
                .orElseThrow(() -> new DefaultException("Nie znaleziono zadania", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Long createTaskInProject(Long projectId, TaskDTO newTask, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);
        Project project = projectService.getProjectById(projectId);

        Task task = mapTaskDTOToTask(newTask, project);
        Task createdTask = taskRepository.save(task);

        return createdTask.getId();
    }


    public Long updateTask(Long projectId, Long taskId, TaskDTO updatedTask, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);
        Project project = projectService.getProjectById(projectId);

        Task task = mapTaskDTOToTask(updatedTask, project);
        task.setId(taskId);

        Task savedUpdatedTask = taskRepository.save(task);

        return savedUpdatedTask.getId();
    }

    public void deleteTask(Long projectId, Long taskId, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);

        taskRepository.deleteById(taskId);
    }


    private TaskDTO mapTaskToTaskDTO(Task task) {
        return TaskDTO.builder()
                .name(task.getName())
                .description(task.getDescription())
                .priority(task.getPriority())
                .startDate(task.getStartDate())
                .estimatedCompletionDate(task.getEstimatedCompletionDate())
                .build();
    }

    private Task mapTaskDTOToTask(TaskDTO task, Project project) {
        return Task.builder()
                .name(task.getName())
                .description(task.getDescription())
                .priority(task.getPriority())
                .startDate(task.getStartDate())
                .estimatedCompletionDate(task.getEstimatedCompletionDate())
                .project(project)
                .build();
    }
}
