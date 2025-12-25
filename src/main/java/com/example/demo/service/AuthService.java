package com.example.demo.service;

import com.example.demo.dto.AuthRequestDto;

public interface AuthService {

    String register(AuthRequestDto request);

    String login(AuthRequestDto request);
}
