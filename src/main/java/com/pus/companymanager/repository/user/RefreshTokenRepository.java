package com.pus.companymanager.repository.user;

import com.pus.companymanager.model.user.RefreshToken;
import com.pus.companymanager.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    void deleteByUser(User user);
}
