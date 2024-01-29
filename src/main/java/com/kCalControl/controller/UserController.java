package com.kCalControl.controller;

import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.RetrieveUserDTO;
import com.kCalControl.dto.user.RetrieveUsersDTO;
import com.kCalControl.dto.credentials.UpdateCredentialsDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/web", "/mobile"})
public interface UserController {
    @GetMapping("get-data/{id}")
    @ResponseBody
    ResponseEntity<RetrieveUserDTO> getUserData(@PathVariable Integer id);
    @PostMapping("signup")
    ResponseEntity<String> createUser(@RequestBody NewUserDTO dto);
    @GetMapping("search-user")
    @ResponseBody
    ResponseEntity<RetrieveUsersDTO> getUsers(@RequestParam Integer page, @RequestParam Integer size,
                                              @RequestParam String sort, @RequestParam @Nullable String query,
                                              @RequestParam @Nullable String searchBy);
    @PutMapping("update-user-data/{id}")
    ResponseEntity<String> updateUserData(@PathVariable("id") Integer id, @RequestBody UpdateUserDataDTO dto);
    @PutMapping("update-credentials/{id}")
    ResponseEntity<String> updateCredentials(@PathVariable("id") Integer id, @RequestBody UpdateCredentialsDTO dto);
    @DeleteMapping("delete-user/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") Integer id);
}
