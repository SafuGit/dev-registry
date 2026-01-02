package com.safu.dev_registry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safu.dev_registry.models.Project;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  List<Project> findByName(String name);
  List<Project> findByUserId(long userId);
}
