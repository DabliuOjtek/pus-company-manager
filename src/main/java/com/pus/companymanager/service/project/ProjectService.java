package com.pus.companymanager.service.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.ProjectDTO;
import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.project.Member;
import com.pus.companymanager.model.project.Project;
import com.pus.companymanager.repository.project.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;
    private MemberService memberService;

    public List<ProjectDTO> getAllUserProject(UserDetailsImpl userDetails) {
        return memberService.getAllMembershipForUser(userDetails).stream()
                .map(Member::getProject)
                .map(this::mapProjectToProjectDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectDetails(Long projectId, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);

        return projectRepository.findById(projectId)
                .map(this::mapProjectToProjectDTO)
                .orElseThrow(() -> new DefaultException("Brak projektu o podanym id", HttpStatus.NOT_FOUND));
    }

    public Long createProject(ProjectDTO newProject, UserDetailsImpl userDetails) {
       Project project = mapProjectDTOToProject(newProject);

       Project createdProject = projectRepository.save(project);
       memberService.createMember(createdProject, userDetails, true);
       return createdProject.getId();
    }

    public Long updateProject(Long projectId, ProjectDTO projectToUpdate, UserDetailsImpl userDetails) {
        memberService.isOwnerOfProject(projectId, userDetails);

        Project project = mapProjectDTOToProject(projectToUpdate);
        project.setId(projectId);

        Project projectUpdated = projectRepository.save(project);
        return projectUpdated.getId();
    }

    public void deleteProject(Long projectId, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);

        projectRepository.deleteById(projectId);
    }

    private ProjectDTO mapProjectToProjectDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .completionDate(project.getCompletionDate())
                .build();
    }

    private Project mapProjectDTOToProject(ProjectDTO projectDTO) {
        return Project.builder()
                .name(projectDTO.getName())
                .description(projectDTO.getDescription())
                .startDate(projectDTO.getStartDate())
                .completionDate(projectDTO.getCompletionDate())
                .build();
    }
}
