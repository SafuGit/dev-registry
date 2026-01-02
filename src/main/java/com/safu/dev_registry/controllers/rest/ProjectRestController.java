package com.safu.dev_registry.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safu.dev_registry.dto.project.ProjectResponse;
import com.safu.dev_registry.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectRestController {
  private final ProjectService projectService;

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<ProjectResponse>> getProjectsByUserId(@PathVariable long userId) {
    return projectService.getProjectsByUserId(userId);
  }
}
