package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponseDto register(AuthRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        user.setActive(true);

        userRepository.save(user);

        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                Set.of(user.getRole())
        );

        return new AuthResponseDto(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("email not found"));

        if (!user.isActive()) {

            throw new BadRequestException("inactive user");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("invalid password");
        }

        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                Set.of(user.getRole())
        );

        return new AuthResponseDto(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
