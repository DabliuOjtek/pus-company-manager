package com.pus.companymanager.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pus.companymanager.exception.ExceptionBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        String message = "Problem with authentication";
        try {
            if ((boolean) httpServletRequest.getAttribute("tokenExpired")) {
                message = "Token Expired";
                sendError(httpServletResponse, message);
            }
        } catch (NullPointerException ex) {
            sendError(httpServletResponse, message);
        }
    }

    private void sendError(HttpServletResponse httpServletResponse, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String error = objectMapper.writer().writeValueAsString(new ExceptionBody(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                message
        ));
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write(error);
    }
}
