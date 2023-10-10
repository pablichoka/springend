package com.kCalControl.controller;

import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api")
public interface UserDBController {
    @PostMapping("signup")
    ResponseEntity<String> createNormalUser(@RequestBody NewUserDTO dto);

    @GetMapping("user/whoiam")
    @ResponseBody
    ResponseEntity<String> whoIAm();

    @GetMapping("user/userData/{id}")
    @ResponseBody
    ResponseEntity<String> getLoggedUserData(@PathVariable ObjectId id);

    @DeleteMapping("user/deleteUser/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") ObjectId id);

    @PutMapping("user/updateUserData/{id}")
    ResponseEntity<String> updateUserData(@PathVariable("id") ObjectId id, @RequestBody UpdateUserDataDTO dto);

    @PutMapping("user/updatePassword/{id}")
    ResponseEntity<String> updatePassword(@PathVariable("id") ObjectId id, @RequestBody UpdatePasswordDTO dto);
}
