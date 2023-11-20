package com.kCalControl.controller;

import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.RetrieveUserDTO;
import com.kCalControl.dto.user.RetrieveUsersDTO;
import com.kCalControl.dto.credentials.UpdateCredentialsDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public interface UserController {
    @PostMapping("signup")
    ResponseEntity<String> createUser(@RequestBody NewUserDTO dto);

    @GetMapping("get-data/{id}")
    @ResponseBody
    ResponseEntity<RetrieveUserDTO> getUserData(@PathVariable Integer id);

    @DeleteMapping("delete-user/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") Integer id);

    @PutMapping("update-user-data/{id}")
    ResponseEntity<String> updateUserData(@PathVariable("id") Integer id, @RequestBody UpdateUserDataDTO dto);

    @PutMapping("update-password/{id}")
    ResponseEntity<String> updateCredentials(@PathVariable("id") Integer id, @RequestBody UpdateCredentialsDTO dto);

    @PostMapping("list-user")
    @ResponseBody
    ResponseEntity<RetrieveUsersDTO> getUsersList(@RequestBody SearchParamsDTO dto);

    //TODO fix pagination: page entity adds the search result to the existing pageable
    @PostMapping("search-users")
    @ResponseBody
    ResponseEntity<RetrieveUsersDTO> getUsersFromSearch(@RequestBody SearchParamsDTO dto);
}
