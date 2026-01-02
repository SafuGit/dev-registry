package com.safu.dev_registry.dto.project;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.safu.dev_registry.models.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  Project toProject(AddProjectRequest addProjectRequest);

  @Mapping(target = "userId", source = "user.id")
  ProjectResponse toProjectResponse(Project project);
  List<ProjectResponse> toProjectResponseList(List<Project> projects);
}
