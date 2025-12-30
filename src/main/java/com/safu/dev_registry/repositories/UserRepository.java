package com.safu.dev_registry.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safu.dev_registry.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
}
