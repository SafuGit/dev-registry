package com.safu.dev_registry.services;

import org.springframework.stereotype.Service;

import com.safu.dev_registry.repositories.UserRepository;

@Service
public class AuthService {
  public final UserRepository userRepository;

  

  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
