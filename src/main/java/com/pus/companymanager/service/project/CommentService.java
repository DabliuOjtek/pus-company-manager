package com.pus.companymanager.service.project;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.project.CommentDTO;
import com.pus.companymanager.model.project.Comment;
import com.pus.companymanager.model.project.Member;
import com.pus.companymanager.model.project.Task;
import com.pus.companymanager.repository.project.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private MemberService memberService;
    private TaskService taskService;

    public List<CommentDTO> getComments(Long projectId, Long taskId, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);

        return commentRepository.findAllByTaskId(taskId)
                .stream()
                .map(this::mapCommentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createCommentToTask(Long projectId, Long taskId, CommentDTO newComment, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);

        Member member = memberService.getMemberForProject(userDetails, projectId);
        Task task = taskService.getTaskById(taskId);
        Comment comment = mapCommentDTOToComment(newComment, task, member);

        Comment createdComment = commentRepository.save(comment);
        return createdComment.getId();
    }

    public void deleteComment(Long projectId, Long commentId, UserDetailsImpl userDetails) {
        memberService.isMemberOfProject(projectId, userDetails);
        memberService.isMemberOfComment(commentId, userDetails);

        commentRepository.deleteById(commentId);
    }

    private CommentDTO mapCommentToCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .comment(comment.getComment())
                .build();
    }

    private Comment mapCommentDTOToComment(CommentDTO commentDTO, Task task, Member member) {
        return Comment.builder()
                .comment(commentDTO.getComment())
                .task(task)
                .member(member)
                .build();
    }
}
