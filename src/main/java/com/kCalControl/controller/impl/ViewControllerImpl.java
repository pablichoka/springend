package com.kCalControl.controller.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.controller.ViewController;
import com.kCalControl.dto.NewUserDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.UserDBService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ViewControllerImpl implements ViewController {

    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    Checker checker;
    @Autowired
    UserDBService userDBService;
    @Override
    public String index(){
        return "index";
    }

    @Override
    public String home(Model model) {
        UserDB userDB = userDBService.returnLoggedUser();
        model.addAttribute("user", userDB);
        return "/views/home";
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @Override
    public String addUserFromAdmin(Model model){
        model.addAttribute("id", userDBRepository.findByUsername(userDBService.getUsernameLoggedUser()).get().getId());
        model.addAttribute("user", new NewUserDTO());
        return "/admin/addNewUser";
    }

    @Override
    public String showDashboard(Model model) {
        model.addAttribute("user", userDBService.returnLoggedUser());
        return "/views/dashboard";
    }
}
