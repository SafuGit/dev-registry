package com.safu.dev_registry.dto.auth;

import lombok.Data;

@Data
public class AuthResponse {
  private long id;
  private String username;
  private String email;
  private String token;
}
