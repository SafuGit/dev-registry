package com.safu.dev_registry.dto.project;

import java.util.List;

import lombok.Data;

@Data
public class AddProjectRequest {
  private String name;
  private String description;
  private List<String> imageUrls;
  private String projectUrl;
  private List<String> repoUrls;
  private List<String> stack;
  private List<String> features;
}
