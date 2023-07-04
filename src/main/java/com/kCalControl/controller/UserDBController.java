package com.kCalControl.controller;

import com.kCalControl.dto.NewUserDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public interface UserDBController {
    @PostMapping("adminActions/addUser")
    String createAdminUser(@RequestParam("id") ObjectId id, @RequestParam("role") String role, NewUserDTO dto, Model model);
    @PostMapping("addUser")
    String createNormalUser(@RequestParam("id") ObjectId id, NewUserDTO dto, Model model);
    @GetMapping("userActions/myProfile")
    String myProfile(Principal principal, Model model);
    @GetMapping("userActions/listUser")
    String getUsersList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int pageSize, Model model); //these values have to be synchronized with JS file

    //TODO implement a view with two forms collapsed, one for user data and the other one for personal data
    @GetMapping("userActions/editUser/{id}")
    String editUser(@PathVariable("id") ObjectId id, Model model, Principal principal);
    @GetMapping("userActions/deleteUser/{id}")
    void deleteUser(@PathVariable("id") ObjectId id);

}
