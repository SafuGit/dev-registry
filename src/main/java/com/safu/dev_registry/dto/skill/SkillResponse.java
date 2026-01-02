package com.safu.dev_registry.dto.skill;

import lombok.Data;

@Data
public class SkillResponse {
  private long id;
  private String name;
  private String category;
  private String iconUrl;
  private boolean isConfident;
  private long userId;
}
