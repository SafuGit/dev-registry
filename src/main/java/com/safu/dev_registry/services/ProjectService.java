package com.safu.dev_registry.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.safu.dev_registry.dto.project.AddProjectRequest;
import com.safu.dev_registry.dto.project.ProjectMapper;
import com.safu.dev_registry.dto.project.ProjectResponse;
import com.safu.dev_registry.models.Project;
import com.safu.dev_registry.models.User;
import com.safu.dev_registry.repositories.ProjectRepository;
import com.safu.dev_registry.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;
  private final UserRepository userRepository;

    public ResponseEntity<ProjectResponse> addProject(AddProjectRequest request) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null || !authentication.isAuthenticated()) {
        return ResponseEntity.status(401).build();
      }
      Project project = projectMapper.toProject(request);
      System.out.println(authentication.getName());
      Optional<User> user = userRepository.findByEmail(authentication.getName());
      if (user.isEmpty()) {
        return ResponseEntity.status(401).build();
      }
      project.setUser(user.get());
      projectRepository.save(project);
      ProjectResponse response = projectMapper.toProjectResponse(project);
      return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<ProjectResponse>> getProjectsByUserId(long userId) {
      List<Project> projects = projectRepository.findByUserId(userId);
      List<ProjectResponse> responses = projectMapper.toProjectResponseList(projects);
      return ResponseEntity.ok(responses);
    }
}
