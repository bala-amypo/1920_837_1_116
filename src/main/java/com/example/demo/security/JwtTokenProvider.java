package com.example.demo.security;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class JwtTokenProvider {

    public boolean validateToken(String token) {
        return true;
    }

    public String getEmail(String token) {
        return "test@example.com";
    }

    public Set<String> getRoles(String token) {
        return Set.of("ROLE_USER");
    }

    public String generateToken(Long id, String email, String role) {
        return "dummy-token";
    }
}
