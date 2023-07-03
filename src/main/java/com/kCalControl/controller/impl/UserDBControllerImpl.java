package com.kCalControl.controller.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.controller.UserDBController;
import com.kCalControl.dto.NewUserDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
import com.kCalControl.dto.UpdateUserDataDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.service.UserDBService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Service
public class UserDBControllerImpl implements UserDBController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AssetsRepository assetsRepository;
    @Autowired
    Checker checker;
    @Autowired
    UserDBService userDBService;
    @Override
    public String createAdminUser(@RequestParam("id") ObjectId id, @RequestParam("role") String role, NewUserDTO dto, Model model){
        if(!checker.checkRoleAdminById(id, model)){
            return "error/403";
        }
        UserDB newUserDB = userDBService.newUser(id, dto, role);
        assetsRepository.save(newUserDB.getAssets());
        userRepository.save(newUserDB);
        return "redirect:/home";
    }

    @Override
    public String createNormalUser(@RequestParam("id") ObjectId id, NewUserDTO dto, Model model){
        UserDB newUserDB = userDBService.newUser(id, dto, "USER");
        assetsRepository.save(newUserDB.getAssets());
        userRepository.save(newUserDB);
        return "redirect:/home";
    }

    @Override
    public String myProfile(Principal principal, Model model){
        UserDB returnedUser = userDBService.returnLoggedUser(principal);
        model.addAttribute("user", returnedUser);
        return "/userActions/myProfile";
    }

    @Override
    public String editUser(ObjectId id, Model model, Principal principal) {
        if (!checker.checkSameUser(principal, id, model)) {
            if (!checker.checkRoleAdminByPrincipal(principal, model)) {
                return "error/403";
            }
        }
        UserDB returnedUser = userDBService.returnUserById(id);
        model.addAttribute("user", returnedUser);
        model.addAttribute("userData", new UpdateUserDataDTO());
        model.addAttribute("personalData", new UpdatePersonalDataDTO());
        return "/userActions/editUser";
    }

    @Override
    public void deleteUser(ObjectId id){
        userDBService.deleteUser(id);
    }

    @Override
    public String getUsersList(int page, int pageSize, Model model){
        Page<UserDB> usersList = userDBService.getUsers(page, pageSize);
        model.addAttribute("users", usersList.getContent());
        model.addAttribute("last", usersList.isLast());
        return "/userActions/listUser";
    }

}