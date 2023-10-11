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
@RolesAllowed("ADMIN")
public interface AdminController {

    @PostMapping("add-user")
    ResponseEntity<String> createAdminUser(@RequestBody NewUserDTO dto);

    @GetMapping("user-data/{id}")
    @ResponseBody
    ResponseEntity<RetrieveUserDTO> getUserData(@PathVariable ObjectId id);

    @PutMapping("update-data/{id}")
    ResponseEntity<String> updateUserData(@PathVariable ObjectId id, @RequestBody UpdateUserDataDTO dto);

    @PutMapping("update-password/{id}")
    ResponseEntity<String> updatePassword(@PathVariable ObjectId id, @RequestBody UpdatePasswordDTO dto);

    @DeleteMapping("delete-user/{id}")
    ResponseEntity<String> deleteUser(@PathVariable ObjectId id);

    @PostMapping("list-user")
    @ResponseBody
    ResponseEntity<RetrieveUsersDTO> getUsersList(@RequestBody SearchParamsDTO dto);

    //TODO fix pagination: page entity adds the search result to the existing pageable
    //TODO check parameters of JS functions passed to the server. Probably I have to include searchParams info into requests

    @PostMapping("search-users")
    @ResponseBody
    ResponseEntity<RetrieveUsersDTO> getUsersFromSearch(@RequestBody SearchParamsDTO dto);
}
