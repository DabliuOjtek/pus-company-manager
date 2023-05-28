package com.pus.companymanager.service.authorization.user;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.user.UserDTO;
import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.user.User;
import com.pus.companymanager.repository.user.UserRepository;
import com.pus.companymanager.service.authorization.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void modifyUser(UserDTO userDTO, UserDetailsImpl userDetails) {

        if(!userDTO.getEmail().equals(userDetails.getUsername()) &&
                userRepository.existsByEmail(userDTO.getEmail())){
            throw new DefaultException("Email użytkownika musi byc unikalny", HttpStatus.BAD_REQUEST);
        }

        User currentUser = getUser(userDetails);
        currentUser.setCity(userDTO.getCity());
        currentUser.setEmail(userDTO.getEmail());
        currentUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        currentUser.setFirstName(userDTO.getFirstName());
        currentUser.setLastName(userDTO.getLastName());
        currentUser.setCountry(userDTO.getCountry());
        currentUser.setCity(userDTO.getCity());
        currentUser.setStreet(userDTO.getStreet());
        currentUser.setZipCode(userDTO.getZipCode());

        userRepository.save(currentUser);
        authorizationService.generateTokensForUser(currentUser);
    }

    public User getUser(UserDetailsImpl userDetails) {
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new DefaultException("Użytkownik nie istnieje", HttpStatus.NOT_FOUND));
    }
}
