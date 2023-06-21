package com.kCalControl.controller;

import com.kCalControl.dto.UserDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RolesAllowed({"USER","ADMIN"})
@RequestMapping("/userActions")
public class UserActionsController {

    @Autowired
    UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserActionsController.class);

    @GetMapping("/editUser/{id}")
    private String editUser(@PathVariable("id") ObjectId id, Model model, Principal principal) {

        Optional<UserDB> userDBOptional = userRepository.findById(id);
        if (!userDBOptional.isPresent()) {
            model.addAttribute("error", "User not found.");
            return "error/404";
        }

        if (!userRepository.findByUsername(principal.getName()).get().getId().equals(id)) {
            if(!userRepository.findByUsername(principal.getName()).get().getRoleName().equals("ADMIN")) {
                model.addAttribute("error", "You do not have permission to edit this user.");
                return "error/403";
            }
        }

        UserDB userDB = userDBOptional.get();
        UserDTO userDTO = userDB.UserDB2UserDTO();
        UserDTO userDT0mold = new UserDTO();

        model.addAttribute("user2edit", userDTO);
        model.addAttribute("user", userDT0mold);
        return "userAdminActions/editUser";
    }

    @GetMapping("/myProfile")
    private String myProfile(Model model, Principal principal){
        Optional<UserDB> userDBOptional = userRepository.findByUsername(principal.getName());
        if(!userDBOptional.isPresent()){
            model.addAttribute("error", "User not found.");
            return "error/404";
        }

        UserDTO userDTO = userDBOptional.get().UserDB2UserDTO();
        model.addAttribute("user", userDTO);

        return "userActions/myProfile";
    }



}
