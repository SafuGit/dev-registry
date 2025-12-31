package com.safu.dev_registry.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safu.dev_registry.dto.project.AddProjectRequest;

@Controller
@RequestMapping("/project")
public class ProjectController {
  @GetMapping("/add")
  public String getAddProjectPage(Model model) {
    model.addAttribute("addProjectRequest", new AddProjectRequest());
    return "project/add";
  }
}
