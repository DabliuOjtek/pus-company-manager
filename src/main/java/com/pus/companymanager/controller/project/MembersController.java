package com.pus.companymanager.controller.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects/{projectId}/members")
public class MembersController {

    @GetMapping
    public List<MemberDTO> getMembers(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ArrayList<>();
    }

    @PostMapping
    public MemberDTO createTask(@PathVariable Long projectId, @RequestBody MemberDTO newMember, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new MemberDTO();
    }

    @DeleteMapping(name = "/{memberId}")
    public void deleteTask(@PathVariable Long projectId, @PathVariable Long memberId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

    }
}
