package com.pus.companymanager.controller.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.CommentDTO;
import com.pus.companymanager.service.project.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/tasks/{taskId}/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentDTO> getComments(@PathVariable Long projectId, @PathVariable Long taskId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.getComments(projectId, taskId, userDetails);
    }

    @PostMapping
    public ResponseEntity<Long> createComment(@PathVariable Long projectId, @PathVariable Long taskId,
                                              @RequestBody CommentDTO newComment, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long commentId = commentService.createCommentToTask(projectId, taskId, newComment, userDetails);
        return new ResponseEntity<>(commentId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(projectId, commentId, userDetails);
    }
}
