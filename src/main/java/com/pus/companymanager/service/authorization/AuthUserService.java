package com.pus.companymanager.service.authorization;

import com.pus.companymanager.configuration.security.UserDetailsImpl;
import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.user.User;
import com.pus.companymanager.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Błędne dane uwierzytelniające"));

        if (!user.isActive()) {
            throw new DefaultException("Błędne dane uwierzytelniające", HttpStatus.UNAUTHORIZED);
        }

        return UserDetailsImpl.build(user);
    }
}
