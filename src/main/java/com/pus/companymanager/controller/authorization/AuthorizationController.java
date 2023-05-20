package com.pus.companymanager.controller.authorization;

import com.pus.companymanager.dto.authorization.ConfirmationDTO;
import com.pus.companymanager.dto.authorization.JWTokenDTO;
import com.pus.companymanager.dto.user.UserAuthDTO;
import com.pus.companymanager.dto.user.UserDTO;
import com.pus.companymanager.service.authorization.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("registration")
    public ResponseEntity<ConfirmationDTO> registerUser(@RequestBody @Valid UserDTO registrationData) {
        ConfirmationDTO confirmation = authorizationService.registerUserAsInactive(registrationData);
        return new ResponseEntity<>(confirmation, HttpStatus.CREATED);
    }

    @PostMapping("registration/confirm")
    public void confirmUserRegistration(@RequestBody @Valid ConfirmationDTO confirmationDTO) {
        authorizationService.confirmRegistration(confirmationDTO);
    }

    @PostMapping("login")
    public JWTokenDTO loginUserWithGettingToken(@Valid @RequestBody UserAuthDTO userAuthDTO) {
        return authorizationService.authenticateAndGetToken(userAuthDTO);
    }

    @PostMapping("refresh")
    public JWTokenDTO refreshToken(@Valid @RequestBody String refreshToken) {
        return authorizationService.refreshToken(refreshToken);
    }
}
