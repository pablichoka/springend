package com.kCalControl.controller;

import com.kCalControl.dto.creation.NewUserDTO;
import com.kCalControl.dto.search.SearchParamsDTO;
import com.kCalControl.dto.update.UpdatePasswordDTO;
import com.kCalControl.dto.update.UpdateUserDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public interface UserDBController {
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin/addUser")
    void createAdminUser(@RequestParam("id") ObjectId id, @RequestParam("role") String role, NewUserDTO dto, Model model, HttpServletResponse response);

    //TODO re-evaluate pagination method
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin/listUser")
    String getUsersList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize, Model model); //these values have lower priority than the JS ones

    //TODO fix pagination: page entity adds the search result to the existing pageable
    //TODO check parameters of JS functions passed to the server. Probably I have to include searchParams info into requests
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin/listUser")
    String searchUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize,
                       SearchParamsDTO dto, Model model, HttpServletResponse response);

    @PostMapping("signup")
    void createNormalUser(@RequestBody NewUserDTO dto);

    @GetMapping("user/whoiam")
    @ResponseBody
    ResponseEntity<String> whoIAm();

    @GetMapping("user/deleteUser/{id}")
    void deleteUser(@PathVariable("id") ObjectId id, HttpServletResponse response);

    @PostMapping("user/updateUserData")
    void updateUserData(@PathVariable("id") ObjectId id, UpdateUserDataDTO dto, Model model, HttpServletResponse response);

    @PostMapping("user/updatePassword")
    void updatePassword(@PathVariable("id") ObjectId id, UpdatePasswordDTO dto, Model model, HttpServletResponse response);
}
