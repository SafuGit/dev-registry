package com.safu.dev_registry.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.safu.dev_registry.config.JWTService;
import com.safu.dev_registry.dto.auth.AuthMapper;
import com.safu.dev_registry.dto.auth.AuthResponse;
import com.safu.dev_registry.dto.auth.LoginRequest;
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
    System.out.println("(AuthService): Starting user registration for email: " + request.getEmail());
    try {
      System.out.println("(AuthService): Checking if user exists with email: " + request.getEmail());
      if (userRepository.existsByEmail(request.getEmail())) {
        System.out.println("(AuthService): User already exists with email: " + request.getEmail());
        return ResponseEntity.badRequest().build();
      }

      System.out.println("(AuthService): Mapping register request to user");
      User user = authMapper.toUser(request);
      System.out.println("(AuthService): Encoding password for user: " + user.getEmail());
      user.setPassword(
        passwordEncoder.encode(request.getPassword())
      );

      System.out.println("(AuthService): Saving user to repository");
      user = userRepository.save(user);

      System.out.println("(AuthService): Generating JWT token for user: " + user.getEmail());
      String token = jwtService.generateToken(user);

      System.out.println("(AuthService): Creating auth response");
      AuthResponse response = authMapper.toAuthResponse(user);
      response.setToken(token);

      System.out.println("(AuthService): Registration successful for user: " + user.getEmail());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      System.out.println("(AuthService): Exception occurred during registration: " + e.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }

  public ResponseEntity<AuthResponse> login(LoginRequest request) {
    System.out.println("(AuthService): Starting user login for email: " + request.getEmail());
    try {
      System.out.println("(AuthService): Checking if user exists with email: " + request.getEmail());
      if (!userRepository.existsByEmail(request.getEmail())) {
        System.out.println("(AuthService): User does not exist with email: " + request.getEmail());
        return ResponseEntity.badRequest().build();
      }
      System.out.println("(AuthService): Retrieving user from repository");
      User user = userRepository.findByEmail(request.getEmail()).get();
      System.out.println("(AuthService): Verifying password for user: " + user.getEmail());
      if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        System.out.println("(AuthService): Password mismatch for user: " + user.getEmail());
        return ResponseEntity.badRequest().build();
      }
      System.out.println("(AuthService): Generating JWT token for user: " + user.getEmail());
      String token = jwtService.generateToken(user);
      System.out.println("(AuthService): Creating auth response");
      AuthResponse response = authMapper.toAuthResponse(user);
      response.setToken(token);
      System.out.println("(AuthService): Login successful for user: " + user.getEmail());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      System.out.println("(AuthService): Exception occurred during login: " + e.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }
}
