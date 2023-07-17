package com.kCalControl.controller.impl;

import com.kCalControl.controller.UserBMController;
import com.kCalControl.service.UserBMService;
import com.kCalControl.service.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserBMControllerImpl implements UserBMController {
    @Autowired
    UserDBService userDBService;
    @Autowired
    UserBMService userBMService;

    @Override
    public String bmCalculator(Model model) {
        model.addAttribute("user", userDBService.returnLoggedUser());
        model.addAttribute("userBM", userBMService.returnBMLoggedUser());
        return "/views/calcBM";
    }
}
