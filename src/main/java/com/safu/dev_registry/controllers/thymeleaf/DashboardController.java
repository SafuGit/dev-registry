package com.safu.dev_registry.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
  @GetMapping("/")
  public String getDashboard() {
    return "index";
  }
}
