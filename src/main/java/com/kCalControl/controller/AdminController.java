package com.kCalControl.controller;

import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.RetrieveUserDTO;
import com.kCalControl.dto.user.RetrieveUsersDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public interface AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("addUser")
    ResponseEntity<Void> createAdminUser(@RequestBody NewUserDTO dto);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("userData/{id}")
    @ResponseBody
    ResponseEntity<RetrieveUserDTO> getUserData(@PathVariable ObjectId id);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("listUser")
    ResponseEntity<RetrieveUsersDTO> getUsersList(@RequestBody SearchParamsDTO dto);

    //TODO fix pagination: page entity adds the search result to the existing pageable
    //TODO check parameters of JS functions passed to the server. Probably I have to include searchParams info into requests
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("searchUsers")
    ResponseEntity<RetrieveUsersDTO> getUsersFromSearch(@RequestBody SearchParamsDTO dto);
}
