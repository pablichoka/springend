package com.kCalControl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public interface UserBMController {

    @GetMapping("/views/calcBM")
    String bmCalculator(Model model);

}
