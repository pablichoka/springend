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

    //TODO implement an endpoint to receive the data from the form, save it and return by ajax request the page with updated data
    //TODO create DTO for the form
    @Override
    public String bmCalculator(Model model) {
        model.addAttribute("user", userDBService.returnLoggedUser());
        model.addAttribute("userBM", userBMService.returnBMLoggedUser());
        return "/views/calcBM";
    }
}
