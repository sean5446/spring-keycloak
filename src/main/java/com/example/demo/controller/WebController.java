package com.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

}
