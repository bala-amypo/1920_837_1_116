package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenProvider {

    public Claims getClaims(String token) {
        return Jwts.parser().parseClaimsJwt(token).getBody();
    }
}
