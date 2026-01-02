package com.safu.dev_registry.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.safu.dev_registry.dto.skill.AddSkillRequest;
import com.safu.dev_registry.models.Skill;
import com.safu.dev_registry.models.User;
import com.safu.dev_registry.repositories.SkillRepository;
import com.safu.dev_registry.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService {
  private final SkillRepository skillRepository;
  private final UserRepository userRepository;

  private static final Pattern SKILL_PATTERN = Pattern.compile(
      "^(.+?)\\s*\\(category:\\s*(.+?)\\)\\s*\\(iconUrl:\\s*(.+?)\\)\\s*\\(isConfident:\\s*(true|false)\\)$"
  );

  public ResponseEntity<String> addSkill(AddSkillRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return ResponseEntity.status(401).body("Unauthorized");
    }
    Optional<User> user = userRepository.findByEmail(authentication.getName());
    if (user.isEmpty()) {
      return ResponseEntity.status(401).body("User not found");
    }

    Skill skill = parseSkill(request.getSkillInput());
    if (skill == null) {
      return ResponseEntity.badRequest().body("Invalid skill format");
    }
    skill.setUser(user.get());
    skillRepository.save(skill);
    return ResponseEntity.ok("Skill added successfully");
  }

  private Skill parseSkill(String input) {
    Matcher matcher = SKILL_PATTERN.matcher(input.trim());
    if (matcher.matches()) {
      Skill skill = new Skill();
      skill.setName(matcher.group(1).trim());
      skill.setCategory(matcher.group(2).trim());
      skill.setIconUrl(matcher.group(3).trim());
      skill.setConfident(Boolean.parseBoolean(matcher.group(4).trim()));
      return skill;
    }
    return null;
  }

  public ResponseEntity<List<Skill>> getSkillsByUserId(long userId) {
    List<Skill> skills = skillRepository.findByUserId(userId);
    return ResponseEntity.ok(skills);
  }
}