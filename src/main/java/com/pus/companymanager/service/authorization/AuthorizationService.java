package com.pus.companymanager.service.authorization;

import com.pus.companymanager.configuration.security.JWTUtils;
import com.pus.companymanager.dto.authorization.ConfirmationDTO;
import com.pus.companymanager.dto.authorization.JWTokenDTO;
import com.pus.companymanager.dto.user.UserAuthDTO;
import com.pus.companymanager.dto.user.UserDTO;
import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.user.Confirmation;
import com.pus.companymanager.model.user.User;
import com.pus.companymanager.repository.user.ConfirmationRepository;
import com.pus.companymanager.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUnits;

    @Transactional
    public ConfirmationDTO registerUserAsInactive(UserDTO registrationData) {
        if (isEmailUnique(registrationData.getEmail())) {
            User newUser = mapUserDTOToUser(registrationData);
            userRepository.save(newUser);
            return createConfirmCode(newUser);
        } else {
            throw new DefaultException("Nie można utworzyć użytkownika");
        }
    }

    public void confirmRegistration(ConfirmationDTO confirmation) {
        Confirmation confirm = confirmationRepository.getConfirmationByConfirmCode(confirmation.getCode())
                .orElseThrow(() -> new DefaultException("Kod nie istnieje lub wygasł"));

        if (confirm.getExpireDate().isAfter(LocalDateTime.now())) {
            confirmationRepository.delete(confirm);
            throw new DefaultException("Kod nie istnieje lub wygasł");
        }

        User userToActive = confirm.getUser();
        userToActive.setActive(true);
        userRepository.save(userToActive);
        confirmationRepository.delete(confirm);
    }

    public JWTokenDTO authenticateAndGetToken(UserAuthDTO userAuthDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthDTO.getEmail(), userAuthDTO.getPassword()
        ));
        String accessToken = jwtUnits.generateAccessToken(authentication);
        String refreshToken = jwtUnits.generateRefreshToken(authentication);
        return new JWTokenDTO(accessToken, refreshToken);
    }

    public JWTokenDTO refreshToken(String refreshToken) {
        return null;
    }

    private boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    private User mapUserDTOToUser(UserDTO registrationData) {
        return User.builder()
                .email(registrationData.getEmail())
                .password(passwordEncoder.encode(registrationData.getPassword()))
                .firstName(registrationData.getFirstName())
                .lastName(registrationData.getLastName())
                .country(registrationData.getCountry())
                .city(registrationData.getCity())
                .street(registrationData.getStreet())
                .zipCode(registrationData.getZipCode())
                .build();
    }

    private ConfirmationDTO createConfirmCode(User newUser) {
        Confirmation confirmation = Confirmation.builder()
                .user(newUser)
                .confirmCode(RandomStringUtils.randomAlphanumeric(6))
                .expireDate(LocalDateTime.now().plusMinutes(10L))
                .build();
        confirmationRepository.save(confirmation);

        return new ConfirmationDTO(confirmation.getConfirmCode());
    }

}
