package com.pus.companymanager.service.authorization.user;

import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.model.user.RefreshToken;
import com.pus.companymanager.model.user.User;
import com.pus.companymanager.repository.user.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public String saveUserRefreshTokenAndReturnUUID(User user) {
        String uuid = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken(uuid, user);
        refreshTokenRepository.deleteByUser(user);
        refreshTokenRepository.save(refreshToken);

        return uuid;
    }

    public User findUserByUUID(String uuid) {
        return refreshTokenRepository.findById(uuid)
                .map(RefreshToken::getUser)
                .orElseThrow(() -> new DefaultException("Błędny lub przedawniony token", HttpStatus.UNAUTHORIZED));
    }
}
