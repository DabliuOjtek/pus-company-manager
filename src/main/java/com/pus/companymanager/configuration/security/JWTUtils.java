package com.pus.companymanager.configuration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class JWTUtils {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.jwtAccessExpire}")
    private int expireAccessToken;

    @Value("${app.jwt.jwtRefreshExpire}")
    private int expireRefreshToken;

    public String generateAccessToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireAccessToken))
                .sign(Algorithm.HMAC256(secret));
    }

    public String generateRefreshTokenForUUID(String uuid) {
        return JWT.create()
                .withSubject(uuid)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireRefreshToken))
                .sign(Algorithm.HMAC256(secret));
    }

    public DecodedJWT verifyToken(String token) {
        DecodedJWT verifiedToken = null;
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            try {
                verifiedToken = JWT.require(Algorithm.HMAC256(secret))
                        .build()
                        .verify(token.replace("Bearer ", ""));
            } catch (TokenExpiredException ex) {
                throw ex;
            } catch (JWTVerificationException ex) {
                return null;
            }
        }

        return verifiedToken;
    }

    public UserDetailsImpl getUserFromToken(DecodedJWT decodedJWT) {
        return new UserDetailsImpl(decodedJWT.getSubject(), null);
    }
}
