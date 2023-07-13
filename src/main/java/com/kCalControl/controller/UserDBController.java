package com.kCalControl.controller;

import com.kCalControl.dto.NewUserDTO;
import com.kCalControl.dto.UpdatePasswordDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
import com.kCalControl.dto.UpdateUserDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public interface UserDBController {
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin/addUser")
    String createAdminUser(@RequestParam("id") ObjectId id, @RequestParam("role") String role, NewUserDTO dto, Model model);
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin/listUser")
    String getUsersList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int pageSize, Model model); //these values have to be synchronized with JS file
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin/listUser/search")
    String searchUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam("query") String filter, Model model);
    @PostMapping("signUp")
    String createNormalUser(@RequestParam("id") ObjectId id, NewUserDTO dto, Model model);
    @GetMapping("api/myProfile")
    String myProfile(Model model);

    //TODO implement a view with two forms collapsed, one for user data and the other one for personal data
    @GetMapping("api/editUser/{id}")
    String editUser(@PathVariable("id") ObjectId id, Model model, Principal principal);
    @GetMapping("api/deleteUser/{id}")
    void deleteUser(@PathVariable("id") ObjectId id, HttpServletResponse response);
    @PostMapping("api/updateUserData/{id}")
    void updateUserData(@PathVariable("id")ObjectId id, UpdateUserDataDTO dto, Model model, HttpServletResponse response);
    @PostMapping("api/updatePersonalData/{id}")
    void updatePersonalData(@PathVariable("id")ObjectId id, UpdatePersonalDataDTO dto, Model model, HttpServletResponse response);
    @PostMapping("api/updatePassword/{id}")
    void updatePassword(@PathVariable("id")ObjectId id, UpdatePasswordDTO dto, Model model, HttpServletResponse response);
}
