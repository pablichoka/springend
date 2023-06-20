package com.kCalControl.controller;

import com.kCalControl.dto.UserDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RolesAllowed({"ADMIN", "USER"})
@RequestMapping("/userActions")
public class UserActionsController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/editUser/{id}")
    private String editUser(@PathVariable("id") ObjectId id, Model model, Principal principal) {

        Optional<UserDB> userDBOptional = userRepository.findById(id);
        if (!userDBOptional.isPresent()) {
            throw new UsernameNotFoundException("Cannot retrieve data from this user");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (userRepository.findByUsername(authentication.getName()).get().getId() != id) {
//            if (!authentication.getAuthorities().contains("ROLE_ADMIN")) {
                model.addAttribute("error", "You do not have permission to edit this user.");
                return "error/403";
            }
//        }

        UserDB userDB = userDBOptional.get();
        UserDTO userDTO = userDB.UserDB2UserDTO();
        UserDTO userDT0mold = new UserDTO();

        model.addAttribute("user2edit", userDTO);
        model.addAttribute("user", userDT0mold);
        return "userActions/editUser";
    }

//    @PostMapping("/updateUserData")
//    private String updateUserData(@RequestParam("user") UserDTO userDTO){
//        Optional<UserDB> userDBOptional = userRepository.findById(userDTO.getId());
//        if(!userDBOptional.isPresent()){
//            throw new UsernameNotFoundException("Cannot update data for this user.");
//        }
//        UserDB userDB = userDBOptional.get();
//        userDB
//    }

}
