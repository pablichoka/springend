package com.kCalControl.controller;

import com.kCalControl.dto.UserDTO;
import com.kCalControl.model.Assets;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RolesAllowed("ADMIN")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/newUser/signUpForm")
    private String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "actions/signUpForm";
    }

    @PostMapping("/addUser")
    private String addUserToDb(@ModelAttribute("user") UserDTO userDTO, Principal principal){

        String encPass = passwordEncoder.encode(userDTO.getPassword());
        UserDB userDB = new UserDB(userDTO.getUsername(),userDTO.getF_name(),userDTO.getL_name(),userDTO.getMobile(), userDTO.getEmail(), encPass);
        userDB.setPasswordDate(LocalDateTime.now());

        Optional<UserDB> optionalUserDB = userRepository.findByFirstName(principal.getName());

        Assets assets = new Assets();

        assets.setCreationDate(LocalDateTime.now());
        assets.setModificationDate(LocalDateTime.now());
        assets.setCreationPerson(optionalUserDB.get());
        assets.setModificationPerson(optionalUserDB.get());

        userDB.setAssets(assets);

        userRepository.save(userDB);

        return "home";
    }

}
