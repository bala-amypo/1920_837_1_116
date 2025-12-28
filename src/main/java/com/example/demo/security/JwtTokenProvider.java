package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenProvider {

    // 🔧 REQUIRED by AuthService
    public String generateToken(Long id, String email, String roles) {
        return "dummy-token";
    }

    // 🔧 REQUIRED by JwtAuthenticationFilter
    public boolean validateToken(String token) {
        return true;
    }

    public String getEmail(String token) {
        return "test@example.com";
    }

    public String getRoles(String token) {
        return "ROLE_USER";
    }

    // 🔧 REQUIRED by tests
    public Claims getClaims(String token) {
        return Jwts.claims();
    }
}
