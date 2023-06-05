//package com.kCalControl.controller;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class CustomErrorController{
//
//    @RequestMapping("/error")
//    public String handleError(Model model) {
//        Object errorMessage = model.getAttribute("error");
//
//        if (errorMessage instanceof String) {
//            String error = (String) errorMessage;
//            if (error.startsWith("3")) {
//                return "3xx";
//            } else if (error.equals("403")) {
//                return "403";
//            } else if (error.equals("404")) {
//                return "404";
//            } else if (error.startsWith("4")) {
//                return "4xx";
//            }
//        }
//
//        return "error-5xx";
//    }
//
//}
