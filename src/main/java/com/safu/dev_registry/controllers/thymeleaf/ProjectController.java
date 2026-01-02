package com.safu.dev_registry.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safu.dev_registry.dto.project.AddProjectRequest;
import com.safu.dev_registry.services.ProjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
  private final ProjectService projectService;

  @GetMapping("/add")
  public String getAddProjectPage(Model model) {
    model.addAttribute("addProjectRequest", new AddProjectRequest());
    return "project/add";
  }

  @PostMapping("/add")
  public String addProject(@ModelAttribute AddProjectRequest addProjectRequest) {
    projectService.addProject(addProjectRequest);
    return "redirect:/";
  }
}
