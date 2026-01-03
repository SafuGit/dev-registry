package com.safu.dev_registry.controllers.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<List<SkillResponse>> getSkillsByUserId(
      @PathVariable long userId,
      @RequestParam(required = false) String categories) {
    
    // Parse category order from comma-separated list (e.g., categories=Frontend,Backend,Languages)
    Map<String, Integer> categoryOrder = new HashMap<>();
    if (categories != null && !categories.isBlank()) {
      String[] categoryArray = categories.split(",");
      for (int i = 0; i < categoryArray.length; i++) {
        categoryOrder.put(categoryArray[i].trim(), i + 1);
      }
    }
    
    return skillService.getSkillsByUserId(userId, categoryOrder);
  }
}
