package com.safu.dev_registry.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safu.dev_registry.dto.auth.LoginRequest;
import com.safu.dev_registry.dto.auth.RegisterRequest;
import com.safu.dev_registry.services.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  public String registerUser(@ModelAttribute RegisterRequest user, HttpServletResponse response) {
    var authResponse = authService.register(user);
    if (authResponse.getStatusCode().is2xxSuccessful() && authResponse.getBody() != null) {
      Cookie jwtCookie = new Cookie("jwt", authResponse.getBody().getToken());
      jwtCookie.setHttpOnly(true);
      jwtCookie.setPath("/");
      jwtCookie.setMaxAge(86400); // 1 day
      response.addCookie(jwtCookie);
      return "redirect:/";
    }
    return "redirect:/auth/register?error";
  }

  @PostMapping("/login")
  public String loginUser(@ModelAttribute LoginRequest user, HttpServletResponse response) {
    var authResponse = authService.login(user);
    if (authResponse.getStatusCode().is2xxSuccessful() && authResponse.getBody() != null) {
      Cookie jwtCookie = new Cookie("jwt", authResponse.getBody().getToken());
      jwtCookie.setHttpOnly(true);
      jwtCookie.setPath("/");
      jwtCookie.setMaxAge(86400); // 1 day
      response.addCookie(jwtCookie);
      return "redirect:/";
    }
    return "redirect:/auth/login?error";
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

  @GetMapping("/logout")
  public String logout(HttpServletResponse response) {
    // Clear the JWT cookie
    Cookie jwtCookie = new Cookie("jwt", "");
    jwtCookie.setHttpOnly(true);
    jwtCookie.setPath("/");
    jwtCookie.setMaxAge(0); // Expire immediately
    response.addCookie(jwtCookie);
    return "redirect:/auth/login";
  }
}
