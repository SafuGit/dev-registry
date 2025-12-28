package com.safu.dev_registry.config;

import com.safu.dev_registry.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JWTService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long expiration;

  public String generateToken(User user) {

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiration);

    return Jwts.builder()
        .subject(user.getEmail())
        .issuedAt(now)
        .expiration(expiryDate)
        .claim("userId", user.getId())
        .claim("username", user.getUsername())
        .signWith(getSigningKey())
        .compact();
  }

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(
        secret.getBytes(StandardCharsets.UTF_8));
  }
}
