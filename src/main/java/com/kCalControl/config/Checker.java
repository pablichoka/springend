package com.kCalControl.config;

import com.kCalControl.repository.UserDBRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class Checker {

    @Autowired
    UserDBRepository userDBRepository;

    public boolean checkRoleAdminByPrincipal(Principal principal) {
        return userDBRepository.findByUsername(principal.getName())
                .map(user -> user.getRoleName().equals("ADMIN"))
                .orElse(false);
    }

    public boolean checkRoleAdminById(ObjectId id) {
        return userDBRepository.findById(id)
                .map(user -> user.getRoleName().equals("ADMIN"))
                .orElse(false);
    }

    public boolean checkUserExistsByPrincipal(Principal principal) {
        return userDBRepository.findByUsername(principal.getName()).isPresent();
    }

    public boolean checkUserExistsById(ObjectId id) {
        return userDBRepository.findById(id).isPresent();
    }

    public boolean checkSameUser(ObjectId id, Principal principal) {
        return userDBRepository.findById(new ObjectId(principal.getName()))
                .map(user -> user.getId().equals(id))
                .orElse(false);
    }
}
