package com.pus.companymanager.repository.project;

import com.pus.companymanager.model.project.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUserIdAndProjectId(Long userId, Long projectId);
}
