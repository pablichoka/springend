package com.kCalControl.config;

import com.kCalControl.repository.UserDBRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.security.Principal;

public class Checker {

    @Autowired
    UserDBRepository userDBRepository;

    public boolean checkRoleAdminByPrincipal(Principal principal, Model model){
        if (!userDBRepository.findByUsername(principal.getName()).get().getRoleName().equals("ADMIN")) {
            model.addAttribute("error", "You do not have permission to edit this user.");
            return false;
        }else{
            return true;
        }
    }
    public boolean checkRoleAdminById(ObjectId id, Model model){
        if (!userDBRepository.findById(id).get().getRoleName().equals("ADMIN")) {
            model.addAttribute("error", "You do not have permission to edit this user.");
            return false;
        }else{
            return true;
        }
    }
    public boolean checkUserExistsByPrincipal(Principal principal, Model model){
        if (!userDBRepository.findByUsername(principal.getName()).isPresent()) {
            model.addAttribute("error", "User not found.");
            return false;
        }else{
            return true;
        }
    }
    public boolean checkUserExistsById(ObjectId id, Model model){
        if (!userDBRepository.findById(id).isPresent()) {
            model.addAttribute("error", "User not found.");
            return false;
        }else{
            return true;
        }
    }
    public boolean checkSameUser(Principal principal, ObjectId id, Model model){
        if (!userDBRepository.findByUsername(principal.getName()).get().getId().equals(id)) {
            model.addAttribute("error", "You do not have permission to edit this user.");
            return false;
        }
        else{
            return true;
        }
    }
}
