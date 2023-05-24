package com.kCalControl.controller;

import com.kCalControl.dto.UserDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/listUser")
    private String listUser(Model model){

        UserDTO userDTO = new UserDTO();

        List<UserDB> userDBList = (List<UserDB>) userRepository.findAll();
        List<UserDTO> userDTOList = userDBList.stream().map(u -> u.UserDB2UserDTO()).toList();
        model.addAttribute("users", userDTOList);

        return "actions/listUsers";
    }

}
