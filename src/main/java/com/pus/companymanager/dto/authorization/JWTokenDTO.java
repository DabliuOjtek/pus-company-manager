package com.pus.companymanager.dto.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTokenDTO {
    private String accessToken;
    private String refreshToken;
}
