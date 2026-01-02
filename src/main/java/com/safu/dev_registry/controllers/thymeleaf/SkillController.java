package com.safu.dev_registry.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safu.dev_registry.dto.skill.AddSkillRequest;
import com.safu.dev_registry.services.SkillService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {
  private final SkillService skillService;

  @GetMapping("/add")
  public String getAddSkillPage(Model model) {
    model.addAttribute("addSkillRequest", new AddSkillRequest());
    return "skills/add";
  }

  @PostMapping("/add")
  public String addSkill(@ModelAttribute AddSkillRequest addSkillRequest) {
    skillService.addSkills(addSkillRequest);
    return "redirect:/";
  }
}