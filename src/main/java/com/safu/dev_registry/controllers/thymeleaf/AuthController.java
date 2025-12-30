package com.safu.dev_registry.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safu.dev_registry.dto.auth.LoginRequest;
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

  @PostMapping("/login")
  public String loginUser(@ModelAttribute LoginRequest user) {
    authService.login(user);
    return "redirect:/";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("RegisterRequest", new RegisterRequest());
    return "/auth/register";
  }

  @GetMapping("/login")
  public String getLoginPage(Model model) {
    model.addAttribute("LoginRequest", new LoginRequest());
    return "/auth/login";
  }
}
