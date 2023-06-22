package com.kCalControl.controller;

import com.kCalControl.config.Checker;
import com.kCalControl.dto.UserDTO;
import com.kCalControl.model.Assets;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RolesAllowed({"USER", "ADMIN"})
@RequestMapping("/userActions")
public class UserActionsController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AssetsRepository assetsRepository;
    @Autowired
    Checker checker;

    private final static Logger logger = LoggerFactory.getLogger(UserActionsController.class);

    @GetMapping("/editUser/{id}")
    private String editUser(@PathVariable("id") ObjectId id, Model model, Principal principal) {

        Optional<UserDB> userDBOptional = userRepository.findById(id);

        if (!checker.checkUserExistsByPrincipal(principal, model)) {
            return "error/404";
        }

        if (!checker.checkSameUser(principal, id, model)) {
            if (!checker.checkRoleAdminByPrincipal(principal, model)) {
                return "error/403";
            }

        }

        UserDB userDB = userDBOptional.get();
        UserDTO userDTO = userDB.UserDB2UserDTO();
        UserDTO userDT0empty = new UserDTO();

        model.addAttribute("user2edit", userDTO);
        model.addAttribute("user", userDT0empty);
        return "userActions/editUser";
    }

    @GetMapping("/myProfile")
    private String myProfile(Model model, Principal principal) {
        Optional<UserDB> userDBOptional = userRepository.findByUsername(principal.getName());
        if (!userDBOptional.isPresent()) {
            model.addAttribute("error", "User not found.");
            return "error/404";
        }

        UserDTO userDTO = userDBOptional.get().UserDB2UserDTO();
        model.addAttribute("user", userDTO);

        return "userActions/myProfile";
    }

    @PostMapping("/updateUserData/{id}")
    private String updateUserData(@ModelAttribute("user") UserDTO userDTO, @PathVariable("id") ObjectId id, Model model, Principal principal) {
        Optional<UserDB> userDBOptional = userRepository.findById(id);
        Optional<UserDB> userDBOptional1 = userRepository.findByUsername(principal.getName());

        if(!checker.checkUserExistsByPrincipal(principal, model) || !checker.checkUserExistsById(id,model)){
            return "error/404";
        }

        UserDB modUserDB = userDBOptional1.get();
        UserDB userDB = userDBOptional.get();
        if (!userDTO.getEmail().isBlank()) {
            userDB.setEmail(userDTO.getEmail());
        }
        if (!userDTO.getPassword().isBlank()) {
            userDB.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            userDB.setPasswordDate(LocalDateTime.now());
        }
        if (!userDTO.getMobile().isBlank()) {
            userDB.setMobile(userDTO.getMobile());
        }
        if (userDTO.getAge() != null) {
            userDB.setAge(userDTO.getAge());
        }
        if (userDTO.getWeight() != null) {
            userDB.setWeight(userDTO.getWeight());
        }
        if (userDTO.getHeight() != null) {
            userDB.setHeight(userDTO.getHeight());
        }
        if (!userDTO.getGender().isBlank()) {
            userDB.setGender(userDTO.getGender());
        }

        Assets assets = userDB.getAssets();

        assets.setModificationDate(LocalDateTime.now());
        assets.setModificationPerson(modUserDB);

        userDB.setAssets(assets);

        assetsRepository.save(assets);
        userRepository.save(userDB);

        if (checker.checkRoleAdminById(modUserDB.getId(), model)) {
            return "redirect:/adminActions/listUser";
        } else {
            return "redirect:/userActions/myProfile";
        }
    }

}
