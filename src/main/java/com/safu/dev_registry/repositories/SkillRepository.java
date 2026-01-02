package com.safu.dev_registry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safu.dev_registry.models.Skill;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
  List<Skill> findByName(String name);
  List<Skill> findByUserId(long userId);
}