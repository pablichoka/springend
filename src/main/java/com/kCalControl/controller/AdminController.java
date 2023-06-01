package com.kCalControl.controller;

import com.kCalControl.dto.UserDTO;
import com.kCalControl.model.Assets;
import com.kCalControl.model.IdClases.UserRoleId;
import com.kCalControl.model.UserDB;
import com.kCalControl.model.UserRole;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.repository.UserRoleRepository;
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

@Controller
@RolesAllowed("ADMIN")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping("/adminActions/signUpForm")
    private String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "adminActions/signUpForm";
    }

    @PostMapping("/adminActions/addUser")
    private String addUserToDb(@ModelAttribute("user") UserDTO userDTO, Principal principal){

        String encPass = passwordEncoder.encode(userDTO.getPassword());
        UserDB userDB = new UserDB(userDTO.getUsername(),userDTO.getFirstName(),userDTO.getLastName(),userDTO.getMobile(), userDTO.getEmail(), encPass, userDTO.getAge(), userDTO.getWeight());
        userDB.setPasswordDate(LocalDateTime.now());

        UserDB optionalUserDB = userRepository.findByUsername(principal.getName()).get();

        Assets assets = new Assets();

        assets.setCreationDate(LocalDateTime.now());
        assets.setModificationDate(LocalDateTime.now());
        assets.setCreationPerson(optionalUserDB);
        assets.setModificationPerson(optionalUserDB);

        userDB.setAssets(assets);

        UserRoleId userRoleId = new UserRoleId(userDB,roleRepository.findById("ADMIN").get());
        UserRole userRole = new UserRole(userRoleId);

        userRoleRepository.save(userRole);
        userRepository.save(userDB);

        return "home";
    }

}
