package com.pus.companymanager.repository.project;

import com.pus.companymanager.model.project.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTaskId(Long taskId);

    boolean existsByMemberIdAndId(Long memberId, Long commentId);
}
