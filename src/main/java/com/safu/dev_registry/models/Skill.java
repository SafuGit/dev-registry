package com.safu.dev_registry.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "skills")
public class Skill {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String category;
  private String iconUrl;
  private boolean isConfident;

  @Column(name = "priority", nullable = true)
  private Integer priority;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
