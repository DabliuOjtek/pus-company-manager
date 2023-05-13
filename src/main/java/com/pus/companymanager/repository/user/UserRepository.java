package com.pus.companymanager.repository.user;

import com.pus.companymanager.dto.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {
    boolean existsByEmail(String email);
}
