package com.pus.companymanager.repository.project;

import com.pus.companymanager.model.project.Member;
import com.pus.companymanager.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUserIdAndProjectId(Long userId, Long projectId);

    boolean existsByUserIdAndProjectIdAndOwner(Long userId, Long projectId, Boolean owner);

    List<Member> findMembersByUser(User user);

    List<Member> findMembersByProjectId(Long projectId);
}
