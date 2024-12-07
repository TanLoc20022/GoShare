package com.example.GoShare.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.GoShare.dto.request.AuthRequest;
import com.example.GoShare.dto.response.ApiResponse;
import com.example.GoShare.dto.response.AuthRespone;
import com.example.GoShare.service.AuthService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthRespone> login(@RequestBody AuthRequest request) {
        String token = authService.login(request);

        AuthRespone jwtAuthResponse = AuthRespone
                .builder()
                .token(token)
                .build();

        return ApiResponse.<AuthRespone>builder()
                .data(jwtAuthResponse)
                .message("Login successful")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}
