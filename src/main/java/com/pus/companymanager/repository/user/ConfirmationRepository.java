package com.pus.companymanager.repository.user;

import com.pus.companymanager.model.user.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Optional<Confirmation> getConfirmationByConfirmCode(String code);
}
