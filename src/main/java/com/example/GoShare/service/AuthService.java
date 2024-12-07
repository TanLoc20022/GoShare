package com.example.GoShare.service;

import com.example.GoShare.dto.request.AuthRequest;

public interface AuthService {
    String login(AuthRequest request);
}
