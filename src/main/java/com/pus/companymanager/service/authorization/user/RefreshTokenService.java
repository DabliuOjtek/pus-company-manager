package com.pus.companymanager.service.authorization.user;

import com.pus.companymanager.model.user.RefreshToken;
import com.pus.companymanager.model.user.User;
import com.pus.companymanager.repository.user.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public String saveUserRefreshTokenAndReturnUUID(User user) {
        String uuid = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken(uuid, user);
        refreshTokenRepository.save(refreshToken);

        return uuid;
    }
}
