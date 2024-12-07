package com.example.GoShare.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.GoShare.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private ObjectMapper objectMapper;

    /**
     * Config error response 401 is not authenticated
     */
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Set content type to JSON
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        // Create an ErrorResponse object
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpServletResponse.SC_UNAUTHORIZED)
                .message(authException.getMessage())
                .build();

        // Mapper the object ErrorRespone to String
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        // Write the response to the response body
        response.getWriter().write(jsonResponse);
    }
}