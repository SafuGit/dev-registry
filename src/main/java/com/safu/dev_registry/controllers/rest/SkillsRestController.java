package com.safu.dev_registry.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safu.dev_registry.dto.skill.SkillResponse;
import com.safu.dev_registry.services.SkillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillsRestController {
  private final SkillService skillService;

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<SkillResponse>> getSkillsByUserId(@PathVariable long userId) {
    return skillService.getSkillsByUserId(userId);
  }
}
