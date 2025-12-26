package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class JwtTokenProvider {
  private static final String SECRET =
    "thisIsASecretKeyForJwtThatIsLongEnoughToBeSecure12345";

  private Key getKey() {
    return Keys.hmacShaKeyFor(SECRET.getBytes());
  }

  public String generateToken(Long userId, String email, String rolesCsv) {
    Map<String,Object> claims = new HashMap<>();
    claims.put("userId", userId);
    claims.put("email", email);
    claims.put("roles", rolesCsv);

    return Jwts.builder()
      .setClaims(claims)
      .setSubject(email)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis()+3600000))
      .signWith(getKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  private Claims claims(String token) {
    return Jwts.parserBuilder().setSigningKey(getKey()).build()
      .parseClaimsJws(token).getBody();
  }

  public Long getUserId(String t){ return ((Number)claims(t).get("userId")).longValue(); }
  public String getEmail(String t){ return (String)claims(t).get("email"); }
  public String getRoles(String t){ return (String)claims(t).get("roles"); }
}
