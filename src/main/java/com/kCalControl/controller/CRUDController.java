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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RolesAllowed("ADMIN")
@RequestMapping("/adminActions")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    private final static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/signUpForm")
    private String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "adminActions/signUpForm";
    }

    @GetMapping("/listUser")
    private String listUser(Model model) {

        UserDTO userDTO = new UserDTO();

        List<UserDB> userDBList = (List<UserDB>) userRepository.findAll();
        List<UserDTO> userDTOList = userDBList.stream().map(u -> u.UserDB2UserDTO()).toList();
        model.addAttribute("users", userDTOList);

        return "adminActions/listUser";
    }

    @PostMapping("/addUser")
    private String addUserToDb(@ModelAttribute("user") UserDTO userDTO, @RequestParam("role") String role, Principal principal){

        String encPass = passwordEncoder.encode(userDTO.getPassword());
        UserDB userDB = new UserDB(userDTO.getUsername(),userDTO.getFirstName(),userDTO.getLastName(),userDTO.getMobile(), userDTO.getEmail(), encPass, userDTO.getAge(), userDTO.getWeight());
        userDB.setPasswordDate(LocalDateTime.now());

        UserDB creationUserDB = userRepository.findByUsername(principal.getName()).get();

        Assets assets = new Assets();

        assets.setCreationDate(LocalDateTime.now());
        assets.setModificationDate(LocalDateTime.now());
        assets.setCreationPerson(creationUserDB);
        assets.setModificationPerson(creationUserDB);

        userDB.setAssets(assets);

        UserRoleId userRoleId = new UserRoleId(userDB,roleRepository.findById(role).get());
        UserRole userRole = new UserRole(userRoleId);

        userRepository.save(userDB);
        userRoleRepository.save(userRole);

        return "home";
    }

    @PostMapping("/deleteUser")
    private String deleteUser(@RequestParam("email") String email, Model model){

        Optional<UserDB> optionalUserDB = userRepository.findByEmail(email);
        if(optionalUserDB.isPresent()){
            Optional<UserRole> optionalUserRole = userRoleRepository.findById_UserDB_Id(optionalUserDB.get().getId());
            userRoleRepository.delete(optionalUserRole.get());
            userRepository.delete(optionalUserDB.get());
            return "redirect:/adminActions/listUser";
        }else{
            model.addAttribute("error", "This user does not exist.");
            return "error/404";
        }
    }
}
