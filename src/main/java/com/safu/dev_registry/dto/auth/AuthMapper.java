package com.safu.dev_registry.dto.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.safu.dev_registry.models.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", expression = "java(new Date(System.currentTimeMillis()))")
  User toUser(RegisterRequest registerRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "username", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  User toUser(LoginRequest loginRequest);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "username", target = "username")
  @Mapping(source = "email", target = "email")
  AuthResponse toAuthResponse(User user);
}
