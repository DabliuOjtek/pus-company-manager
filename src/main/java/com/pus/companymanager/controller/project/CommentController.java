package com.pus.companymanager.controller.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/tasks/{taskId}/comments")
public class CommentController {

    @GetMapping
    public List<CommentDTO> getComments(@PathVariable Long projectId, @PathVariable Long taskId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ArrayList<>();
    }

    @PostMapping
    public CommentDTO createComment(@PathVariable Long projectId, @PathVariable Long taskId,
                                    @RequestBody CommentDTO newComment, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new CommentDTO();
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {

    }
}
