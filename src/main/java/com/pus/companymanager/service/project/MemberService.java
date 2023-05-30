package com.pus.companymanager.service.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.MemberDTO;
import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.project.Member;
import com.pus.companymanager.model.project.Project;
import com.pus.companymanager.model.user.User;
import com.pus.companymanager.repository.project.MemberRepository;
import com.pus.companymanager.repository.project.ProjectRepository;
import com.pus.companymanager.service.authorization.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;
    private UserService userService;
    private ProjectRepository projectRepository;

    public void isMemberOfProject(Long projectId, UserDetailsImpl userDetails) {
        User user = userService.getUser(userDetails);

        if (!memberRepository.existsByUserIdAndProjectId(user.getId(), projectId)) {
            throw new DefaultException("Użytkownik nie należy do projektu", HttpStatus.NOT_FOUND);
        }
    }

    public List<Member> getAllMembershipForUser(UserDetailsImpl userDetails) {
        User user = userService.getUser(userDetails);
        return memberRepository.findMembersByUser(user);
    }

    public void createMember(Project project, UserDetailsImpl userDetails, boolean isOwner) {
        User user = userService.getUser(userDetails);
        createMember(project, user, isOwner);
    }

    private Member createMember(Project project, User user, boolean isOwner) {
        Member newMember = Member.builder()
                .project(project)
                .user(user)
                .owner(isOwner)
                .build();
        return memberRepository.save(newMember);
    }

    public void isOwnerOfProject(Long projectId, UserDetailsImpl userDetails) {
        User user = userService.getUser(userDetails);
        if (!memberRepository.existsByUserIdAndProjectIdAndOwner(user.getId(), projectId, true)) {
            throw new DefaultException("Nie jesteś właścicielem projektu", HttpStatus.NOT_FOUND);
        }
    }

    public List<MemberDTO> getProjectMembers(Long projectId, UserDetailsImpl userDetails) {
        isMemberOfProject(projectId, userDetails);

        return memberRepository.findMembersByProjectId(projectId).stream()
                .map(this::mapMemberToMemberDTO)
                .collect(Collectors.toList());
    }

    private MemberDTO mapMemberToMemberDTO(Member member) {
        return MemberDTO.builder()
                .memberId(member.getId())
                .memberEmail(member.getUser().getEmail())
                .build();
    }

    public Long addProjectMember(Long projectId, MemberDTO newMember, UserDetailsImpl userDetails) {
        isOwnerOfProject(projectId, userDetails);
        User user = userService.getUserByEmail(newMember.getMemberEmail());
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new DefaultException("Brak projektu o podanym id", HttpStatus.NOT_FOUND));

        return createMember(project, user, false).getId();
    }

    public void deleteMemberOfProject(Long projectId, Long memberId, UserDetailsImpl userDetails) {
        isOwnerOfProject(projectId, userDetails);

        memberRepository.deleteById(memberId);
    }
}
