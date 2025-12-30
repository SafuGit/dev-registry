package com.safu.dev_registry.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safu.dev_registry.dto.auth.RegisterRequest;
import com.safu.dev_registry.services.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  public String registerUser(@ModelAttribute RegisterRequest user) {
    authService.register(user);
    return "redirect:/auth/login";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("RegisterRequest", new RegisterRequest());
    return "/auth/register";
  }
}
