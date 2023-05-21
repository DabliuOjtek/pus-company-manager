package com.pus.companymanager.controller.user;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.dto.user.UserDTO;
import com.pus.companymanager.service.authorization.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PutMapping("modify")
    public void confirmUserRegistration(@Valid @RequestBody UserDTO userDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.modifyUser(userDTO, userDetails);
    }
}
