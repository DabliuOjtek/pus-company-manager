package com.pus.companymanager.service.authorization;

import com.pus.companymanager.dto.user.UserDTO;
import com.pus.companymanager.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    @Transactional
    public  registerUserAsInactive(UserDTO registrationData) {
        if(isEmailUnique(registrationData.getEmail())){
            //register
            return
        } else {
        }
    }

    private boolean isEmailUnique(String email) {
        return userRepository.existsByEmail(email);
    }

    private registerUser() {

    }
}
