package com.kCalControl.controller;

import com.kCalControl.dto.user.*;
import com.kCalControl.dto.SearchParamsDTO;
import jakarta.annotation.security.RolesAllowed;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
//TODO substitute PreAuthorize by RolesAllowed
public interface AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("addUser")
    ResponseEntity<String> createAdminUser(@RequestBody NewUserDTO dto);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("userData/{id}")
    @ResponseBody
    ResponseEntity<RetrieveUserDTO> getUserData(@PathVariable ObjectId id);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("updateData/{id}")
    ResponseEntity<String> updateUserData(@PathVariable ObjectId id, @RequestBody UpdateUserDataDTO dto);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("updatePassword/{id}")
    ResponseEntity<String> updatePassword(@PathVariable ObjectId id, @RequestBody UpdatePasswordDTO dto);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("deleteUser/{id}")
    ResponseEntity<String> deleteUser(@PathVariable ObjectId id);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("listUser")
    ResponseEntity<RetrieveUsersDTO> getUsersList(@RequestBody SearchParamsDTO dto);

    //TODO fix pagination: page entity adds the search result to the existing pageable
    //TODO check parameters of JS functions passed to the server. Probably I have to include searchParams info into requests
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("searchUsers")
    ResponseEntity<RetrieveUsersDTO> getUsersFromSearch(@RequestBody SearchParamsDTO dto);
}
