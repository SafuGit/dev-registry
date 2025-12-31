package com.safu.dev_registry.controllers.thymeleaf;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
  @GetMapping("/")
  public String getDashboard() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.isAuthenticated()) {
      return "index";
    } else {
      return "redirect:/auth/login";
    }
  }

  @GetMapping("/addproject")
  public String getAddProjectPage() {
    return "project/add";
  }
}
