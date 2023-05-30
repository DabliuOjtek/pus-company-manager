package com.pus.companymanager.controller.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.MemberDTO;
import com.pus.companymanager.service.project.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/members")
public class MembersController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDTO> getMembers(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memberService.getProjectMembers(projectId, userDetails);
    }

    @PostMapping
    public ResponseEntity<Long> createTask(@PathVariable Long projectId, @RequestBody MemberDTO newMember, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<Long>(memberService.addProjectMember(projectId, newMember, userDetails), HttpStatus.CREATED);
    }

    @DeleteMapping("/{memberId}")
    public void deleteTask(@PathVariable Long projectId, @PathVariable Long memberId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        memberService.deleteMemberOfProject(projectId, memberId, userDetails);
    }
}
