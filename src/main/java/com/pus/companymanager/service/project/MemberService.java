package com.pus.companymanager.service.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.project.Member;
import com.pus.companymanager.model.project.Project;
import com.pus.companymanager.model.user.User;
import com.pus.companymanager.repository.project.MemberRepository;
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

    public void isMemberOfProject(Long projectId, UserDetailsImpl userDetails) {
        User user = userService.getUser(userDetails);
        if (!memberRepository.existsByUserIdAndProjectId(projectId, user.getId())) {
            throw new DefaultException("Użytkownik nie należy do projektu", HttpStatus.NOT_FOUND);
        }
    }

    public List<Member> getAllMembershipForUser(UserDetailsImpl userDetails) {
        User user = userService.getUser(userDetails);
        return memberRepository.findMembersByUser(user);
    }

    public void createMember(Project project, UserDetailsImpl userDetails, boolean isOwner) {
        User user = userService.getUser(userDetails);

        Member newMember = Member.builder()
                .project(project)
                .user(user)
                .owner(isOwner)
                .build();
        memberRepository.save(newMember);
    }

    public void isOwnerOfProject(Long projectId, UserDetailsImpl userDetails) {
        User user = userService.getUser(userDetails);
        if (!memberRepository.existsByUserIdAndProjectIdAndOwner(user.getId(), projectId, true)) {
            throw new DefaultException("Nie jesteś właścicielem projektu", HttpStatus.NOT_FOUND);
        }
    }
}
