package com.safu.dev_registry.dto.project;

import java.util.List;

import lombok.Data;

@Data
public class ProjectResponse {
  private long id;
  private String name;
  private String description;
  private String projectUrl;
  private List<String> imageUrls;
  private List<String> repoUrls;
  private List<String> stack;
  private List<String> features;
  private long userId;
}
