package com.kCalControl.controller;

import com.kCalControl.dto.UserDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RolesAllowed({"ADMIN","USER"})
@RequestMapping("/userActions")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/listUser")
    private String listUser(Model model){

        UserDTO userDTO = new UserDTO();

        List<UserDB> userDBList = (List<UserDB>) userRepository.findAll();
        List<UserDTO> userDTOList = userDBList.stream().map(u -> u.UserDB2UserDTO()).toList();
        model.addAttribute("users", userDTOList);

        return "userActions/listUser";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        UserDB userDB = userRepository.findById(id).get();

        UserDTO userDTO = userDB.UserDB2UserDTO();

        userDTO.setPassword(null);
        userDTO.setUsername(null);
        userDTO.setFirstName(null);
        userDTO.setLastName(null);

        model.addAttribute("id", id);
        model.addAttribute("user", new UserDTO());
        model.addAttribute("user2edit" , userDTO);

        return "userActions/editUser";
    }

    @PostMapping("/updateUserData")
    public String updateUserData(@ModelAttribute("user") UserDTO userDTO, @RequestParam("id") Integer id, Model model, Principal principal){

        UserDB userDB = userRepository.findById(id).get();
        //Empty fields from the form are: String -> ""; Int, Double,...-> null
        if(userDTO.getPassword() != ""){ userDB.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword())); }
        if(userDTO.getEmail() != ""){ userDB.setEmail(userDTO.getEmail()); }
        if(userDTO.getMobile() != ""){ userDB.setMobile(userDTO.getMobile()); }
        if(userDTO.getAge() != null){ userDB.setAge(userDTO.getAge()); }
        if(userDTO.getWeight() != null){ userDB.setWeight(userDTO.getWeight()); }

        userDB.setModificationPerson(userRepository.findByUsername(principal.getName()).get());
        userDB.setModificationDate(LocalDateTime.now());

        userRepository.save(userDB);
        return "redirect:/userActions/listUser";
    }

}
