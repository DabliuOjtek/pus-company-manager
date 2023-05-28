package com.pus.companymanager.service.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.user.User;
import com.pus.companymanager.repository.project.MemberRepository;
import com.pus.companymanager.service.authorization.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
}
