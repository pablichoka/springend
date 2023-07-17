package com.kCalControl.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public interface ViewController {

    @GetMapping("/")
    String index();
    @GetMapping("/views/home")
    String home(Model model);
    @PostMapping("/logout")
    String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
    @GetMapping("/admin/addNewUser")
    String addUserFromAdmin(Model model);
    @GetMapping("/views/dashboard")
    String showDashboard(Model model);
    @GetMapping("/views/calcBM")
    String bmCalculator();
}
