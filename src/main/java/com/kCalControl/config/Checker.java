package com.kCalControl.config;

import com.kCalControl.repository.UserDBRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.security.Principal;

public class Checker {

    @Autowired
    UserDBRepository userDBRepository;

    public boolean checkRoleAdminByPrincipal(Principal principal){
        return userDBRepository.findByUsername(principal.getName()).get().getRoleName().equals("ADMIN");
    }
    public boolean checkRoleAdminById(ObjectId id){
        return userDBRepository.findById(id).get().getRoleName().equals("ADMIN");
    }
    public boolean checkUserExistsByPrincipal(Principal principal){
        return userDBRepository.findByUsername(principal.getName()).isPresent();
    }
    public boolean checkUserExistsById(ObjectId id){
        return userDBRepository.findById(id).isPresent();
    }
    public boolean checkSameUser(Principal principal, ObjectId id){
        return userDBRepository.findById(new ObjectId(principal.getName())).get().getId().equals(id);
    }
}
