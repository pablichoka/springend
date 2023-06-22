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
public class AdminActionsController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AssetsRepository assetsRepository;
    @Autowired
    private Checker checker;

    private final static Logger logger = LoggerFactory.getLogger(AdminActionsController.class);

    @GetMapping("/listUser")
    private String listUser(Model model) {

        UserDTO userDTO = new UserDTO();

        List<UserDB> userDBList = (List<UserDB>) userRepository.findAll();
        List<UserDTO> userDTOList = userDBList.stream().map(u -> u.UserDB2UserDTO()).toList();
        model.addAttribute("users", userDTOList);

        return "adminActions/listUser";
    }

    @PostMapping("/addUser")
    private String addUserToDb(@ModelAttribute("user") UserDTO userDTO, @RequestParam("role") String role, Principal principal) {

        String encPass = passwordEncoder.encode(userDTO.getPassword());
        UserDB userDB = new UserDB(userDTO.getUsername(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getMobile(), userDTO.getEmail(), encPass);
        userDB.setPasswordDate(LocalDateTime.now());

        userDB.setWeight(userDTO.getWeight());
        userDB.setAge(userDTO.getAge());
        userDB.setHeight(userDTO.getHeight());
        userDB.setGender(userDTO.getGender());

        UserDB creationUserDB = userRepository.findByUsername(principal.getName()).get();

        Assets assets = new Assets();
        LocalDateTime localDateTime = LocalDateTime.now();

        assets.setCreationDate(localDateTime);
        assets.setModificationDate(localDateTime);
        assets.setCreationPerson(creationUserDB);
        assets.setModificationPerson(creationUserDB);

        userDB.setAssets(assets);
        userDB.setRole(roleRepository.findByRoleName(role).get());

        assetsRepository.save(assets);
        userRepository.save(userDB);

        return "redirect:/home";
    }

    @GetMapping("/deleteUser/{id}")
    private String deleteUser(@PathVariable("id") ObjectId id, Model model, Principal principal) {

        Optional<UserDB> optionalUserDB = userRepository.findById(id);
        if (checker.checkUserExistsByPrincipal(principal,model)) {
            return "error/404";
        }
        userRepository.deleteById(id);
        return "redirect:/adminActions/listUser";
    }
}
