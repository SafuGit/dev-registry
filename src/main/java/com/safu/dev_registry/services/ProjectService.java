package com.safu.dev_registry.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.safu.dev_registry.dto.project.AddProjectRequest;
import com.safu.dev_registry.dto.project.ProjectMapper;
import com.safu.dev_registry.dto.project.ProjectResponse;
import com.safu.dev_registry.models.Project;
import com.safu.dev_registry.repositories.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;

  public ResponseEntity<ProjectResponse> addProject(AddProjectRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return ResponseEntity.status(401).build();
    }
    Project project = projectMapper.toProject(request);
    projectRepository.save(project);
    ProjectResponse response = projectMapper.toProjectResponse(project);
    return ResponseEntity.ok(response);
  }
}
