package com.pus.companymanager.controller.authorization;

import com.pus.companymanager.dto.authorization.Confirmation;
import com.pus.companymanager.dto.user.UserDTO;
import com.pus.companymanager.service.authorization.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("registration/confirm")
    public ResponseEntity registerUser(@RequestBody @Valid UserDTO registrationData) {
        return authorizationService.registerUserAsInactive(registrationData);
    }

    @PostMapping("registration/confirm")
    public HttpStatus confirmUserRegistration(@RequestBody @Valid Confirmation confirmation) {
        return null;
    }
}
