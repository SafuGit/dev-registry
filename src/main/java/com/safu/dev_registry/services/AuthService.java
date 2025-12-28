package com.safu.dev_registry.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.safu.dev_registry.config.JWTService;
import com.safu.dev_registry.dto.auth.AuthMapper;
import com.safu.dev_registry.dto.auth.AuthResponse;
import com.safu.dev_registry.dto.auth.RegisterRequest;
import com.safu.dev_registry.models.User;
import com.safu.dev_registry.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final AuthMapper authMapper;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;

  public ResponseEntity<AuthResponse> register(RegisterRequest request) {
    try {
      if (userRepository.existsByEmail(request.getEmail())) {
        return ResponseEntity.badRequest().build();
      }

      User user = authMapper.toUser(request);
      user.setPassword(
        passwordEncoder.encode(request.getPassword())
      );

      user = userRepository.save(user);

      String token = jwtService.generateToken(user);

      AuthResponse response = authMapper.toAuthResponse(user);
      response.setToken(token);

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
