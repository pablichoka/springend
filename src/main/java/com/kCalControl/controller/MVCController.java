package com.kCalControl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCController {

    @GetMapping("/")
    private String index(){
        return "index";
    }

    @GetMapping("/home")
    private String home(){
        return "home";
    }

}
