package com.safu.dev_registry.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  private String username;
  private String email;
  private String password; // Must be hashed
  private Date createdAt;

  @OneToMany(mappedBy = "user")
  private List<Skill> skills;

  @OneToMany(mappedBy = "user")
  private List<Project> projects;
}
