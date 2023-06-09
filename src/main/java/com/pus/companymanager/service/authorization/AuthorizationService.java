package com.pus.companymanager.service.authorization;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
import com.pus.companymanager.service.authorization.user.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public ConfirmationDTO registerUserAsInactive(UserDTO registrationData) {
        if (isEmailUnique(registrationData.getEmail())) {
            User newUser = mapUserDTOToUser(registrationData);
            userRepository.save(newUser);
            return createConfirmCode(newUser);
        } else {
            throw new DefaultException("Nie można utworzyć użytkownika", HttpStatus.BAD_REQUEST);
        }
    }

    public void confirmRegistration(ConfirmationDTO confirmation) {
        Confirmation confirm = confirmationRepository.getConfirmationByConfirmCode(confirmation.getCode())
                .orElseThrow(() -> new DefaultException("Kod nie istnieje lub wygasł", HttpStatus.NOT_FOUND));

        if (LocalDateTime.now().isAfter(confirm.getExpireDate())) {
            confirmationRepository.delete(confirm);
            throw new DefaultException("Kod nie istnieje lub wygasł", HttpStatus.NOT_FOUND);
        }

        User userToActive = confirm.getUser();
        userToActive.setActive(true);
        userRepository.save(userToActive);
        confirmationRepository.delete(confirm);
    }

    public JWTokenDTO authenticateAndGetToken(UserAuthDTO userAuthDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthDTO.getEmail(), userAuthDTO.getPassword()
        ));
        User user = userRepository.findByEmail(userAuthDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Błędne dane uwierzytelniające"));

        return generateTokensForUser(user);
    }

    public JWTokenDTO refreshToken(String refreshToken) {
        DecodedJWT verifiedToken;
        try {
            verifiedToken = jwtUnits.verifyToken(refreshToken);
        } catch (TokenExpiredException e) {
            throw new DefaultException("Błędny lub przedawniony token", HttpStatus.UNAUTHORIZED);
        }

        if (verifiedToken != null) {
            User user = refreshTokenService.findUserByUUID(verifiedToken.getSubject());
            return generateTokensForUser(user);
        } else {
            throw new DefaultException("Błędny lub przedawniony token", HttpStatus.UNAUTHORIZED);
        }
    }

    public JWTokenDTO generateTokensForUser(User user) {
        String uuid = refreshTokenService.saveUserRefreshTokenAndReturnUUID(user);
        String accessToken = jwtUnits.generateAccessToken(user.getEmail());
        String refreshToken = jwtUnits.generateRefreshTokenForUUID(uuid);

        return new JWTokenDTO(accessToken, refreshToken);
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
